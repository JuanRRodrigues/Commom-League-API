package br.com.jrr.apiTest.domain.Match;


import br.com.jrr.apiTest.domain.Account.AccountRiotRepository;
import br.com.jrr.apiTest.domain.Account.AccountRiot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import br.com.jrr.apiTest.domain.Match.Repository.MatchInfoLolRiotRepository;
import br.com.jrr.apiTest.configsAPI.ApiKeyManager;
import br.com.jrr.apiTest.domain.API.MatchRegistrationAPI;
import br.com.jrr.apiTest.service.APIConfigService.ConvertData;
import br.com.jrr.apiTest.service.APIConfigService.GetData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MatchIdRiotWebService {

    @Autowired
    private AccountRiotRepository Repository;

    @Autowired
    private MatchInfoLolRiotRepository Repositoryinfo;

    private final GetData get = new GetData();
    private final ConvertData convert = new ConvertData();
    // private final String LINK = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/BepoIV/0264?api_key=RGAPI-52760f41-fb67-441f-aca0-5790dee926f5";
    private final String API_KEY = "?api_key=RGAPI-cf4cebca-d06c-4b84-8137-212a23059557";
    // private final String Region = "americas.";
    //  private final String LINK2 = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id";
    private final String BASE_URL = "https://americas.api.riotgames.com/lol/match/v5/matches/BR1_2930788372?api_key=RGAPI-d873ca05-959e-41c3-b085-5b9c4a0cb10b";


    //  public List<MatchIdDTO> getMatch() {
//        return Repository.findAll()
    //           .stream()
    //              .map(s -> new MatchIdDTO(s.getIdMatch().getIdmatche()))
    //            .collect(Collectors.toList());
    // }



    public List<String> registerByAPI(MatchRegistrationAPI data) throws JsonProcessingException {
        String puiidId = data.puiidId();

        var json = get.obterDados("https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/"+ puiidId + "/ids?start=0&count=20&api_key=" + ApiKeyManager.getApiKey());

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> idMatchList = objectMapper.readValue(json, new TypeReference<List<String>>() {});

        var endereco = "https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/"+ puiidId + "?api_key=" + ApiKeyManager.getApiKey();

        System.out.println(json);
        System.out.println(idMatchList);
        AccountRiot accountRiot = Repository.findByPuuid(puiidId);

        accountRiot.addIdMatches(idMatchList);

        System.out.println(accountRiot);

        return  idMatchList

                ;


    }
}
