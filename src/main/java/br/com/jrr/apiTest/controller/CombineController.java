package br.com.jrr.apiTest.controller;


import br.com.jrr.apiTest.repo2.AccountRiot2Repository;
import br.com.jrr.apiTest.Repository.AccountRiotRepository;
import br.com.jrr.apiTest.domain.Player.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CombineController {

    @Autowired
    AccountRiotRepository accountRiotRepository;

    @Autowired
    AccountRiot2Repository accountRiot2Repository;


    @GetMapping("/teste")
    public Response getResponse(){
        Response response = new Response();
        response.setAccounts( accountRiotRepository.findAll());
        response.setAccounts2( accountRiot2Repository.findAll());
        return response;

    }






}
