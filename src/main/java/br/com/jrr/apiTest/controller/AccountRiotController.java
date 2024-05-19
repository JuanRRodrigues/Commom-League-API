package br.com.jrr.apiTest.controller;

import br.com.jrr.apiTest.Repository.AccountRiotRepository;
import br.com.jrr.apiTest.domain.API.DataAccountRegistrationAPI;
import br.com.jrr.apiTest.domain.Account.AccountMatchRiotDTO;
import br.com.jrr.apiTest.domain.Account.AccountRiotDTO;
import br.com.jrr.apiTest.domain.Account.DadosUpdateDTO;
import br.com.jrr.apiTest.domain.Account.DetailAccount;
import br.com.jrr.apiTest.service.AccountRiotWebService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/accountsRiot")
public class AccountRiotController {

@Autowired
private AccountRiotWebService service;

    @Autowired
    private AccountRiotRepository Repository;

    @GetMapping("/list")
    public List<AccountRiotDTO> getAccount() {
        return service.getAccount();
    }

    @GetMapping("/matches")
    public List<AccountMatchRiotDTO> getAccountMatches() {
        return service.getHistoric();
    }

    @GetMapping("/{id}")
    public AccountRiotDTO getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping("/post")
    public AccountRiotDTO postByAPI(@RequestBody @Valid DataAccountRegistrationAPI data, UriComponentsBuilder uriBuilder){
        return service.registerByAPI(data);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public AccountRiotDTO delete(@PathVariable UUID id){
        Repository.deleteById(id);
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public DetailAccount atualizar(@RequestBody @Valid DadosUpdateDTO dados){
        var account =  Repository.getReferenceById(dados.id());
        account.UpdateAccountDTO(dados);

        return new DetailAccount(account);
    }


}
