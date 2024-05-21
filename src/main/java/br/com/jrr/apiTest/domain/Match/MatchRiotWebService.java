package br.com.jrr.apiTest.domain.Match;



import br.com.jrr.apiTest.domain.Match.Repository.MatchLolRiotRepository;
import br.com.jrr.apiTest.configsAPI.ApiKeyManager;
import br.com.jrr.apiTest.domain.API.DataMatchAPI;
import br.com.jrr.apiTest.domain.DTO.MatchDTO;
import br.com.jrr.apiTest.domain.API.MatchRegistrationAPI;
import br.com.jrr.apiTest.service.APIConfigService.ConvertData;
import br.com.jrr.apiTest.service.APIConfigService.GetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MatchRiotWebService {

    @Autowired
    private MatchLolRiotRepository Repository;

    private final GetData get = new GetData();
    private final ConvertData convert = new ConvertData();


    public List<MatchDTO> getMatch() {
        return Repository.findAll()
               .stream()
                .map(s -> new MatchDTO(s.getMatchId(), s.getGameMode(), s.getMetadado().getParticipants(), s.getInfo().getEndOfGameResult()))
                .collect(Collectors.toList());
    }

    public MatchDTO registerByAPI(MatchRegistrationAPI data) {
        String matchId = data.matchId();
            var json = get.obterDados("https://americas.api.riotgames.com/lol/match/v5/matches/"+ matchId + "?api_key=" + ApiKeyManager.getApiKey());
            DataMatchAPI dataMatchAPI = convert.getDate(json,  DataMatchAPI.class);;
            var match = new MatchEntity(dataMatchAPI);
        System.out.println(json);
        System.out.println(dataMatchAPI);
            MatchEntity savedMatch = Repository.save(match);
        System.out.println("sout" + savedMatch);
            return new MatchDTO(
                    savedMatch.getMatchId(),
                    savedMatch.getGameMode(),
                    savedMatch.getMetadado().getParticipants(),
                    savedMatch.getInfo().getEndOfGameResult()

            );

    }
}
