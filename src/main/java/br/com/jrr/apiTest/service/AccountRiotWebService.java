package br.com.jrr.apiTest.service;

import br.com.jrr.apiTest.Repository.AccountRiotRepository;
import br.com.jrr.apiTest.configs.ApiKeyManager;
import br.com.jrr.apiTest.domain.API.DataAccountAPI;
import br.com.jrr.apiTest.domain.API.DataAccountRegistrationAPI;
import br.com.jrr.apiTest.domain.Account.AccountMatchRiotDTO;
import br.com.jrr.apiTest.domain.Account.AccountRiot;
import br.com.jrr.apiTest.domain.Account.AccountRiotDTO;
import br.com.jrr.apiTest.service.APIConfigService.ConvertData;
import br.com.jrr.apiTest.service.APIConfigService.GetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class AccountRiotWebService {

    @Autowired
    private AccountRiotRepository Repository;

    private final GetData get = new GetData();
    private final ConvertData convert = new ConvertData();
   // private final String LINK = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/BepoIV/0264?api_key=RGAPI-52760f41-fb67-441f-aca0-5790dee926f5";
   private final String API_KEY = "?api_key=RGAPI-d873ca05-959e-41c3-b085-5b9c4a0cb10b";
   // private final String Region = "americas.";
  //  private final String LINK2 = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id";
    private final String BASE_URL = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/";

    public List<AccountRiotDTO> getAccount() {
        return Repository.findAll()
                .stream()
                .map(s -> new AccountRiotDTO(s.getId(), s.getPuuid(), s.getGameName(), s.getTagLine()))
                .collect(Collectors.toList());
    }

    public List<AccountMatchRiotDTO> getHistoric() {
        return Repository.findAll()
                .stream()
                .map(s -> new AccountMatchRiotDTO(s.getId(), s.getPuuid(), s.getGameName(), s.getTagLine(), s.getIdMatchList()))
                .collect(Collectors.toList());
    }

    public AccountRiotDTO getById(UUID id) {
        Optional<AccountRiot> optionalPlayer = Repository.findById(id);
        return optionalPlayer.map(s -> new AccountRiotDTO(s.getId(), s.getPuuid(), s.getGameName(), s.getTagLine()))
                .orElse(null);
    }

    public AccountRiotDTO registerByAPI(DataAccountRegistrationAPI data) {
        String gameName = data.gameName();
        String tagLine = data.tagLine();


            var json = get.obterDados(BASE_URL + gameName + "/" + tagLine + "?api_key=" + ApiKeyManager.getApiKey());
            var endereco = BASE_URL + gameName + "/" + tagLine + "?api_key=" + ApiKeyManager.getApiKey();
            DataAccountAPI dataMediaAPI = convert.getDate(json, DataAccountAPI.class);

            System.out.println(dataMediaAPI);

            var player = new AccountRiot(dataMediaAPI);
            AccountRiot savedPlayer = Repository.save(player);

        System.out.println(endereco);

            return new AccountRiotDTO(
                    savedPlayer.getId(),
                    savedPlayer.getPuuid(),
                    savedPlayer.getGameName(),
                    savedPlayer.getTagLine());
                //    savedPlayer.setIdMatchList(new ArrayList<>()));



    }
}
