package br.com.jrr.apiTest.controller;
import br.com.jrr.apiTest.domain.file.model.File;
import br.com.jrr.apiTest.domain.file.model.FileService;
import br.com.jrr.apiTest.domain.file.model.FileUploadRequest;
import br.com.jrr.apiTest.domain.file.model.FileUploadResponse;
import br.com.jrr.apiTest.domain.user.DTO.UserGeneralEditDTO;
import br.com.jrr.apiTest.domain.user.DTO.UserLanguageEditDTO;
import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.DTO.UserDTO;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import br.com.jrr.apiTest.domain.user.enums.UserRole;
import br.com.jrr.apiTest.enums.Language;
import br.com.jrr.apiTest.enums.adress.City;
import br.com.jrr.apiTest.enums.adress.Country;
import br.com.jrr.apiTest.enums.adress.State;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
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
    public ResponseEntity register(@Valid RegisterDTO data, @RequestParam("file") MultipartFile file) throws IOException {

        // Verifica se o login já existe
        if (this.repository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        // Criptografando a senha
        String encyptedPassword = new BCryptPasswordEncoder().encode(data.password());

        // Criação do arquivo de imagem de perfil
        FileUploadRequest fileUploadRequest = new FileUploadRequest(null, file, data.login());  // Corrigido o parâmetro aqui
        FileUploadResponse fileUploadResponse = fileService.upload(fileUploadRequest);  // Chama o serviço para salvar o arquivo

        // Criando o novo usuário com valores padrões caso algum dado seja null
        User newUser = new User(
                data.login(),
                data.userName() != null ? data.userName() : "User-404",  // Nome padrão se userName for null
                encyptedPassword,
                UserRole.USER,
                data.telefone() != null ? data.telefone() : "000.000.000",  // Telefone padrão
                data.birthDate() != null ? data.birthDate() : LocalDate.now(),  // Data de nascimento padrão (hoje)
                data.cpf() != null ? data.cpf() : "000000000",  // CPF padrão
                data.fullName() != null ? data.fullName() : "Nome Completo",  // Nome completo padrão
                data.team(),
                0.0,
                data.accountRiot(),
                fileUploadResponse.id(),
                Country.BRAZIL,
                City.AUSTIN,
                State.BAHIA,
                Language.ENGLISH,
                data.leagueRegion() // Região padrão
        );

        System.out.println(fileUploadResponse);  // Verificando a resposta com o id do arquivo

        // Salvando o novo usuário no banco de dados
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