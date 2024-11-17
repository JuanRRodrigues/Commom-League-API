package br.com.jrr.apiTest.domain.RiotGames.AccountRiot;

import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.API.DataAccountRegistrationAPI;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/{accountId}/matches")
    public MatchListDTO getMatchList(@PathVariable String accountId) {
        AccountRiotDTO accountRiot = service.getById(accountId);


        return null;
    }

    @GetMapping("/{id}")
    public AccountRiotDTO getById(@PathVariable String id) {
        return service.getById(id);
    }

    // Novo endpoint que retorna o DTO simplificado AssociacaoContaDTO
    @GetMapping("/associacao/{id}")
    public AssociacaoContaDTO getAccountAssociation(@PathVariable String id) {
        // A lógica para encontrar a conta com base no ID e retornar o DTO
        return service.getAccountAssociation(id); // Esse método deve ser implementado no serviço
    }

    @PostMapping("/post")
    public AssociacaoContaDTO postByAPI(@RequestBody @Valid DataAccountRegistrationAPI data, UriComponentsBuilder uriBuilder) throws JsonProcessingException {
        return service.registerByAPI(data);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public AccountRiotDTO delete(@PathVariable String id) {
        Repository.deleteById(id);
        return service.getById(id);
    }

    // Endpoint de busca
    @GetMapping("/search")
    public ResponseEntity<List<AccountRiot>> searchAccounts(@RequestParam(required = false) String gameName,
                                                            @RequestParam(required = false) String tagLine) {
        // Log para depuração
        System.out.println("Searching for gameName: " + gameName);
        System.out.println("Searching for tagLine: " + tagLine);

        // Chama o serviço de busca
        List<AccountRiot> accounts = service.searchAccounts(gameName, tagLine);

        // Se não encontrar nenhuma conta
        if (accounts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Retorna as contas encontradas
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{id}")
    @Transactional
    public DetailAccountDTO atualizar(@RequestBody @Valid DadosUpdateDTO dados) {
        var account = Repository.getReferenceById(dados.id());
        account.updateAccountDTO(dados);

        return new DetailAccountDTO(account);
    }

    @PostMapping("/addAccountRiot")
    @Transactional
    public String addAccounRiot(@RequestBody @Valid addAccountRiotDTO data) {
        return service.addAccountRiot(data);
    }

}
