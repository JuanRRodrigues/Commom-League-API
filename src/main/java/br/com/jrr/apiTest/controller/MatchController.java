package br.com.jrr.apiTest.controller;

import br.com.jrr.apiTest.domain.Match.MatchDTO;
import br.com.jrr.apiTest.domain.API.MatchRegistrationAPI;
import br.com.jrr.apiTest.Repository.MatchLolRiotRepository;
import br.com.jrr.apiTest.service.MatchRiotWebService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/v1/match")
public class MatchController {

@Autowired
private MatchRiotWebService service;

    @Autowired
    private MatchLolRiotRepository Repository;

    @GetMapping("/list")
    public List<MatchDTO> getMatch() {
        return service.getMatch();
    }

    @PostMapping("/post")
    public MatchDTO postByAPI(@RequestBody @Valid MatchRegistrationAPI data, UriComponentsBuilder uriBuilder){
        return service.registerByAPI(data);
    }



}
