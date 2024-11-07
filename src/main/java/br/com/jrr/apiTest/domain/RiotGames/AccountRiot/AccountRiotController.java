package br.com.jrr.apiTest.domain.RiotGames.AccountRiot;

import br.com.jrr.apiTest.controller.TeamAndPlayerDTO;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.API.DataAccountRegistrationAPI;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.AccountMatchRiotDTO;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.AccountRiotDTO;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.DadosUpdateDTO;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.DetailAccountDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/v1/accountsRiot")
public class AccountRiotController {

@Autowired
private AccountRiotWebService service;

    @Autowired
    private AccountRiotRepository Repository;

    @GetMapping("")
    public List<AccountRiotDTO> getAccount() {
        return service.getAccount();
    }

    @GetMapping("/matches")
    public List<AccountMatchRiotDTO> getAccountMatches() {
        return service.getHistoric();
    }

    @GetMapping("/{id}")
    public AccountRiotDTO getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/post")
    public AccountRiotDTO postByAPI(@RequestBody @Valid DataAccountRegistrationAPI data, UriComponentsBuilder uriBuilder){
        return  service.registerByAPI(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public AccountRiotDTO delete(@PathVariable String id){
        Repository.deleteById(id);
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public DetailAccountDTO atualizar(@RequestBody @Valid DadosUpdateDTO dados){
        var account =  Repository.getReferenceById(dados.id());
        account.UpdateAccountDTO(dados);

        return new DetailAccountDTO(account);
    }

    @PostMapping("/addAccountRiot")
    @Transactional
    public AccountRiotDTO addAccounRiot(@RequestBody @Valid TeamAndPlayerDTO data){
        return service.addAccoutnRiot(data);
    }


}
