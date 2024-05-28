package br.com.jrr.apiTest.domain.Account;

import br.com.jrr.apiTest.infra.configsAPI.ApiKeyManager;
import br.com.jrr.apiTest.controller.TeamAndPlayerDTO;
import br.com.jrr.apiTest.domain.API.DataAccountAPI;
import br.com.jrr.apiTest.domain.API.DataAccountRegistrationAPI;
import br.com.jrr.apiTest.domain.DTO.AccountMatchRiotDTO;
import br.com.jrr.apiTest.domain.DTO.AccountRiotDTO;
import br.com.jrr.apiTest.domain.user.User;
import br.com.jrr.apiTest.domain.user.UserRepository;
import br.com.jrr.apiTest.service.APIConfigService.ConvertData;
import br.com.jrr.apiTest.service.APIConfigService.GetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AccountRiotWebService {

    @Autowired
    private AccountRiotRepository Repository;

    @Autowired
    private UserRepository userRepository;

    private final GetData get = new GetData();
    private final ConvertData convert = new ConvertData();
   // private final String LINK = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/BepoIV/0264?api_key=RGAPI-52760f41-fb67-441f-aca0-5790dee926f5";
   private final String API_KEY = "?api_key=RGAPI-d873ca05-959e-41c3-b085-5b9c4a0cb10b";
   // private final String Region = "americas.";
  //  private final String LINK2 = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id";
    private final String BASE_URL = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/";

    private final String BaseURLRiotSummonner = "https://br1.api.riotgames.com/lol/summoner/v4/summoners/";


    public List<AccountRiotDTO> getAccount() {
        return Repository.findAll()
                .stream()
                .map(s -> new AccountRiotDTO(s.getId(), s.getPuuid(), s.getGameName(), s.getTagLine(), s.getIdRiot(), s.getProfileIconId(), s.getSummonerLevel(), s.getRevisionDate(), s.getAccountId()))
                .collect(Collectors.toList());
    }


    public List<AccountMatchRiotDTO> getHistoric() {
        return Repository.findAll()
                .stream()
                .map(s -> new AccountMatchRiotDTO(s.getId(), s.getPuuid(), s.getGameName(), s.getTagLine(), s.getIdMatchList()))
                .collect(Collectors.toList());
    }

    public AccountRiotDTO getById(String id) {
        Optional<AccountRiot> optionalPlayer = Repository.findById(id);
        return optionalPlayer.map(s -> new AccountRiotDTO(s.getId(), s.getPuuid(), s.getGameName(), s.getTagLine(), s.getIdRiot(),s.getRevisionDate(), s.getProfileIconId(), s.getSummonerLevel(), s.getAccountId()))
                .orElse(null);
    }

    public AccountRiotDTO registerByAPI(DataAccountRegistrationAPI data) {
        String gameName = data.gameName();
        String tagLine = data.tagLine();


            var json = get.obterDados(BASE_URL + gameName + "/" + tagLine + "?api_key=" + ApiKeyManager.getApiKey());
            var endereco = BASE_URL + gameName + "/" + tagLine + "?api_key=" + ApiKeyManager.getApiKey();
            DataAccountAPI dataMediaAPI = convert.getDate(json, DataAccountAPI.class);

            var json2 = get.obterDados(BaseURLRiotSummonner + "by-puuid/" + dataMediaAPI.puuid() + "?api_key=" + ApiKeyManager.getApiKey());
            endereco = BaseURLRiotSummonner + "by-puuid/" + dataMediaAPI.puuid() + "?api_key=" + ApiKeyManager.getApiKey();
            DataAccountAPI dataMediaAPI2 = convert.getDate(json2, DataAccountAPI.class);


        System.out.println(endereco);
            System.out.println(json2);
             System.out.println("dataMediaAPI2" + dataMediaAPI2);


        var player = new AccountRiot(dataMediaAPI, dataMediaAPI2);
        AccountRiot savedPlayer = Repository.save(player);


        System.out.println("resultado" + savedPlayer);

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

                );
                //    savedPlayer.setIdMatchList(new ArrayList<>()));



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
