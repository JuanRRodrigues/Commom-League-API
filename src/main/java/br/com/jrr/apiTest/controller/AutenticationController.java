package br.com.jrr.apiTest.controller;


import br.com.jrr.apiTest.domain.user.User;
import br.com.jrr.apiTest.domain.user.UserRepository;
import br.com.jrr.apiTest.domain.user.UserRole;
import br.com.jrr.apiTest.infra.security.TokenService;
import br.com.jrr.apiTest.domain.Team.TeamService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/list")
    public List<UserDTO> getAccount() {
        return teamService.getUser();
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
        User newUser = new User(data.login(), encyptedPassword, UserRole.USER , data.telefone(), data.birthDate(), data.cpf(), data.fullName(), data.team(), 0.0, data.accountRiot());

        System.out.println(newUser);
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}