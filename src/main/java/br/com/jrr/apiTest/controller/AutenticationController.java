package br.com.jrr.apiTest.controller;
import br.com.jrr.apiTest.domain.file.model.FileService;
import br.com.jrr.apiTest.domain.user.DTO.UserGeneralEditDTO;
import br.com.jrr.apiTest.domain.user.DTO.UserLanguageEditDTO;
import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.DTO.UserDTO;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import br.com.jrr.apiTest.domain.user.enums.UserRole;
import br.com.jrr.apiTest.enums.Language;
import br.com.jrr.apiTest.infra.security.TokenService;
import br.com.jrr.apiTest.domain.Team.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AutenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserRepository repository;
    @Autowired
    private FileService fileService;

    @GetMapping("/list")
    public List<UserDTO> getAccount() {
        return teamService.getUser();
    }

    @GetMapping("/filter/{login}")
    public UserDTO getByLogin(@PathVariable String login){
        return teamService.getByLogin(login);
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable String id){
        return teamService.getByIdUser(id);
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser() {
        UserDTO userDTO = teamService.getCurrentUser();

        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/general")
    public String updateUser(@RequestBody UserGeneralEditDTO userDto) {
        return teamService.getAuth(userDto);
    }

    @PutMapping("/language")
    public String updateLanguage(@RequestBody UserLanguageEditDTO userDto) {
        return teamService.editLanguage(userDto);
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String encyptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(),
                data.userName(),
                encyptedPassword,
                UserRole.USER ,
                data.telefone(),
                data.birthDate(),
                data.cpf(),
                data.fullName(),
                data.team(),
                0.0,
                data.accountRiot(),
                "user_default",
                "City",
                "Country",
                "State",
                Language.ENGLISH, data.leagueRegion());
        System.out.println(newUser);
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserGeneralEditDTO userDto) {
        User updatedUser = teamService.updateUser(id, userDto);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Atualizado com Sucesso");
    }






}