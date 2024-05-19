package br.com.jrr.apiTest.controller;


import br.com.jrr.apiTest.domain.user.DataAutentication;
import br.com.jrr.apiTest.domain.user.User;
import br.com.jrr.apiTest.domain.user.UserRepository;
import br.com.jrr.apiTest.domain.user.UserRole;
import br.com.jrr.apiTest.infra.security.DatasTokenJWT;
import br.com.jrr.apiTest.infra.security.TokenService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("auth")
public class AutenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;
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
        User newUser = new User(data.login(), encyptedPassword, UserRole.USER , data.telefone(), data.birthDate(), data.cpf(), data.fullName(), 0.0);

        System.out.println(newUser);
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}