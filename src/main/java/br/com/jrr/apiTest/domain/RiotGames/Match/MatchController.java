package br.com.jrr.apiTest.domain.RiotGames.Match;

import br.com.jrr.apiTest.domain.RiotGames.Match.DTO.MatchDTO;
import br.com.jrr.apiTest.domain.RiotGames.Match.Repository.MatchLolRiotRepository;
import br.com.jrr.apiTest.domain.Torneio.ChampionshipRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ChampionshipRepository championshipRepository;

    @GetMapping("/list")
    public List<MatchDTO> getMatch() {
        return service.getMatch();
    }

    @GetMapping("/by-puuid/{puuid}")
    public List<Match> getMAtchsByPuuid(@PathVariable String puuid) {
        return service.getmatchByPuuid(puuid);
    }

    @GetMapping("/current")
    public ResponseEntity<String> getCurrentMatchById(
            @RequestBody String captainIdBlue,
             String captainRed,
            String championshipId) {



            return service.getCurrentMatchById(captainIdBlue,captainRed,championshipId);

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
