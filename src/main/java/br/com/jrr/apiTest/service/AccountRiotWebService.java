package br.com.jrr.apiTest.service;

import br.com.jrr.apiTest.Repository.AccountRiotRepository;
import br.com.jrr.apiTest.domain.API.DataMediaAPI;
import br.com.jrr.apiTest.domain.API.DataMediaRegistrationAPI;
import br.com.jrr.apiTest.domain.Player.AccountRiot;
import br.com.jrr.apiTest.domain.Player.AccountRiotDTO;
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
    private final String API_KEY = "?api_key=RGAPI-b8033810-2da9-45a5-a125-cc623b79d5fc";
   // private final String Region = "americas.";
  //  private final String LINK2 = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id";
    private final String BASE_URL = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/";

    public List<AccountRiotDTO> getAccount() {
        return Repository.findAll()
                .stream()
                .map(s -> new AccountRiotDTO(s.getId(), s.getPuuid(), s.getGameName(), s.getTagLine()))
                .collect(Collectors.toList());
    }

    public AccountRiotDTO getById(UUID id) {
        Optional<AccountRiot> optionalPlayer = Repository.findById(id);
        return optionalPlayer.map(s -> new AccountRiotDTO(s.getId(), s.getPuuid(), s.getGameName(), s.getTagLine()))
                .orElse(null);
    }

    public AccountRiotDTO registerByAPI(DataMediaRegistrationAPI data) {
        String mediaTitle = data.gameName();
        String mediaType = data.tagLine();
        System.out.println(mediaTitle + mediaType);

        if (mediaTitle != null) {
            var json = get.obterDados(BASE_URL + mediaTitle + "/" + mediaType + API_KEY);
            DataMediaAPI dataMediaAPI = convert.getDate(json, DataMediaAPI.class);

            var player = new AccountRiot(dataMediaAPI);
            AccountRiot savedPlayer = Repository.save(player);



            return new AccountRiotDTO(
                    savedPlayer.getId(),
                    savedPlayer.getPuuid(),
                    savedPlayer.getGameName(),
                    savedPlayer.getTagLine()
            );
        }

        return null;
    }
}
