package br.com.jrr.apiTest.domain.RiotGames.Match;

import br.com.jrr.apiTest.domain.RiotGames.Match.API.MatchRegistrationAPI;
import br.com.jrr.apiTest.domain.RiotGames.Match.DTO.MatchDTO;
import br.com.jrr.apiTest.domain.RiotGames.Match.Participant.Participant;
import br.com.jrr.apiTest.domain.RiotGames.Match.Repository.MatchLolRiotRepository;
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

    @GetMapping("/by-puuid/{puuid}")
    public List<Match> getMAtchsByPuuid(@PathVariable String puuid) {
        return service.getmatchByPuuid(puuid);
    }



    @GetMapping("/{matchId}")
    public Match getMatchById(@PathVariable String matchId) {

        return service.getMatchById(matchId);
    }



    @PostMapping("/post")
    public MatchDTO postByAPI(@RequestBody @Valid String matchID, UriComponentsBuilder uriBuilder){
        return service.registerByAPI(matchID);
    }

}
