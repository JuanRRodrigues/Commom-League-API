package br.com.jrr.apiTest.controller;

import br.com.jrr.apiTest.Repository.AccountRiotRepository;
import br.com.jrr.apiTest.domain.API.DataMediaRegistrationAPI;
import br.com.jrr.apiTest.domain.Player.AccountRiotDTO;
import br.com.jrr.apiTest.service.AccountRiotWebService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/{id}")
    public AccountRiotDTO getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping("/post")
    public AccountRiotDTO postByAPI(@RequestBody @Valid DataMediaRegistrationAPI data, UriComponentsBuilder uriBuilder){
        return service.registerByAPI(data);
    }

  /*  @PutMapping("/edit/{id}")
 /   public PlayerDTO EditSerie(@RequestBody @Valid MediaEditData data){
       var movie = movieRepository.getReferenceById(data.id());
        movie.movieEditData(data);
       movieRepository.save(movie);
       return service.getById(data.id());
    }
*/
    @DeleteMapping("/delete/{id}")
    @Transactional
    public AccountRiotDTO delete(@PathVariable UUID id){
        Repository.deleteById(id);
        return service.getById(id);
    }


}
