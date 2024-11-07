package br.com.jrr.apiTest.domain.RiotGames.AccountRiot;

import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.API.DataAccountAPI;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.API.DataAccountRegistrationAPI;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.AccountMatchRiotDTO;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.AccountRiotDTO;
import br.com.jrr.apiTest.domain.RiotGames.Match.Match;
import br.com.jrr.apiTest.domain.RiotGames.Match.Repository.MatchLolRiotRepository;
import br.com.jrr.apiTest.infra.configsAPI.ApiKeyManager;
import br.com.jrr.apiTest.controller.TeamAndPlayerDTO;
import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import br.com.jrr.apiTest.service.APIConfigService.ConvertData;
import br.com.jrr.apiTest.service.APIConfigService.GetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AccountRiotWebService {

    @Autowired
    private AccountRiotRepository Repository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MatchLolRiotRepository matchRepository;

    private final GetData get = new GetData();
    private final ConvertData convert = new ConvertData();
   // private final String LINK = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/BepoIV/0264?api_key=RGAPI-52760f41-fb67-441f-aca0-5790dee926f5";
   private final String API_KEY = "?api_key=RGAPI-d873ca05-959e-41c3-b085-5b9c4a0cb10b";
   // private final String Region = "americas.";
  //  private final String LINK2 = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id";
    private final String BASE_URL = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/";
    private final String BaseURLRiotSummonner = "https://br1.api.riotgames.com/lol/summoner/v4/summoners/";
    private final String BaseURLRiotMatch = "https://americas.api.riotgames.com/lol/match/v5/matches/";

    public List<AccountRiotDTO> getAccount() {
        return Repository.findAll()
                .stream()
                .map(s -> new AccountRiotDTO(
                        s.getId(),
                        s.getPuuid(),
                        s.getGameName(),
                        s.getTagLine(),
                        s.getIdRiot(),
                        s.getProfileIconId(),
                        s.getSummonerLevel(),
                        s.getRevisionDate(),
                        s.getAccountId()
                     //   s.getMatchList().stream() // Convertendo a lista de Match para MatchDTO
                      //          .map(MatchDTO::fromMatch) // Convertendo cada Match para MatchDTO
                      //          .collect(Collectors.toList()) // Coletando em uma lista de MatchDTO
                ))
                .collect(Collectors.toList());
    }










    public List<AccountMatchRiotDTO> getHistoric() {
        return Repository.findAll()
                .stream()
                .map(s -> new AccountMatchRiotDTO(s.getId(), s.getPuuid(), s.getGameName(), s.getTagLine()))
                .collect(Collectors.toList());
    }

    public AccountRiotDTO getById(String id) {
        Optional<AccountRiot> optionalPlayer = Repository.findById(id);
        return optionalPlayer.map(s -> new AccountRiotDTO(
                s.getId(),
                s.getPuuid(),
                s.getGameName(),
                s.getTagLine(),
                s.getIdRiot(),
                s.getProfileIconId(),
                s.getRevisionDate(),
                s.getSummonerLevel(),
                s.getAccountId()
              //  MatchDTO.fromMatchList(s.getMatchList()) // Aqui você não precisa passar um ID
        )).orElse(null); // Correção aqui
    }

    public AccountRiotDTO registerByAPI(DataAccountRegistrationAPI data) {
        String gameName = data.gameName();
        String tagLine = data.tagLine();


            var json = get.obterDados(BASE_URL + gameName + "/" + tagLine + "?api_key=" + ApiKeyManager.getApiKey());
            DataAccountAPI dataMediaAPI = convert.getDate(json, DataAccountAPI.class);

            var json2 = get.obterDados(BaseURLRiotSummonner + "by-puuid/" + dataMediaAPI.puuid() + "?api_key=" + ApiKeyManager.getApiKey());
            DataAccountAPI dataMediaAPI2 = convert.getDate(json2, DataAccountAPI.class);
        System.out.println("DATA 2:" +dataMediaAPI2);

            var match = get.obterDados(BaseURLRiotMatch + "by-puuid/" + dataMediaAPI.puuid()+ "/ids?start=0&count=20&api_key=" + ApiKeyManager.getApiKey() );
            List<String> dataMatch = convert.getDateAsList(match);


        String ENDERECO = BaseURLRiotMatch + "by-puuid/" + dataMediaAPI.puuid() + "/ids?start=0&count=20&api_key=" + ApiKeyManager.getApiKey();
        System.out.println("matches: " + dataMatch);


        List<Match> matchesSaved = new ArrayList<>();






        var player = new AccountRiot(dataMediaAPI, dataMediaAPI2, matchesSaved);

        for (String matchId : dataMatch) {
            Match matchSaved = new Match();
            matchSaved.setMatchId(matchId); // Certifique-se de que matchId não excede os limites
           // matchSaved.setAccountRiot(player);
            matchesSaved.add(matchSaved);
        }
    //    player.setMatchList(matchesSaved);

      //  System.out.println("tome: " + player.getMatchList(s.getId()));
        AccountRiot savedPlayer = Repository.save(player);
        System.out.println("resultado2: " + savedPlayer);


        Optional<AccountRiot> teste = Repository.findById(savedPlayer.getId());

        System.out.println(teste+"2222");
        return new AccountRiotDTO(
                savedPlayer.getId(),
                savedPlayer.getPuuid(),
                savedPlayer.getGameName(),
                savedPlayer.getTagLine(),
                savedPlayer.getIdRiot(),
                savedPlayer.getProfileIconId(),
                savedPlayer.getRevisionDate(),
                savedPlayer.getSummonerLevel(),
                savedPlayer.getAccountId()
              //  null
        );


    }

    public AccountRiotDTO addAccoutnRiot(TeamAndPlayerDTO data) {
        Optional<AccountRiot> optionalAccountRiot = Repository.findById(data.idTime());
        Optional<User> optionalUser = userRepository.findById(data.idPlayer());

        if (optionalAccountRiot.isPresent() && optionalUser.isPresent()) {
            AccountRiot accountRiot = optionalAccountRiot.get();
            User user = optionalUser.get();

            if (user.getAccountRiot() != null ) {

                System.out.println("Já Cadastrado");
            }else{

                accountRiot.setUser(user);
                user.setAccountRiot(accountRiot);
                userRepository.save(user); //
                Repository.save(accountRiot);
            }

        }
        return null;
    }
}
