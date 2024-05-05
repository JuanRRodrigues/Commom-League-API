package br.com.jrr.apiTest.controller;

import br.com.jrr.apiTest.Repository.AccountRiotRepository;
import br.com.jrr.apiTest.configs.ApiKeyManager;
import br.com.jrr.apiTest.domain.API.DataAccountRegistrationAPI;
import br.com.jrr.apiTest.domain.API.KeyRiotRegistrationAPI;
import br.com.jrr.apiTest.domain.Account.AccountMatchRiotDTO;
import br.com.jrr.apiTest.domain.Account.AccountRiotDTO;
import br.com.jrr.apiTest.domain.Account.DadosUpdateDTO;
import br.com.jrr.apiTest.domain.Account.DetailAccount;
import br.com.jrr.apiTest.service.AccountRiotWebService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/ApiKeyRiot")
public class ApiKeyRiotController {

@Autowired
private AccountRiotWebService service;

    @Autowired
    private AccountRiotRepository Repository;
    @PostMapping("/post")
    public String postByAPI(@RequestBody @Valid KeyRiotRegistrationAPI data, UriComponentsBuilder uriBuilder){
        return ApiKeyManager.setApiKey(data);
    }



}
