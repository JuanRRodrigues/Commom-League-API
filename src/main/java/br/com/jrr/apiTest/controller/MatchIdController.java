package br.com.jrr.apiTest.controller;

import br.com.jrr.apiTest.Repository.MatchLolRiotRepository;
import br.com.jrr.apiTest.domain.API.MatchRegistrationAPI;
import br.com.jrr.apiTest.service.MatchIdRiotWebService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/v1/matchId")
public class MatchIdController {

@Autowired
private MatchIdRiotWebService service;

    @Autowired
    private MatchLolRiotRepository Repository;
    @PostMapping("/post")
    public List<String> postByAPI(@RequestBody @Valid MatchRegistrationAPI data, UriComponentsBuilder uriBuilder) throws JsonProcessingException {
        return service.registerByAPI(data);
    }

}
