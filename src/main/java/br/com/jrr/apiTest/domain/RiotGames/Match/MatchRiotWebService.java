package br.com.jrr.apiTest.domain.RiotGames.Match;



import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.AccountRiot;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.AccountRiotRepository;
import br.com.jrr.apiTest.domain.RiotGames.Match.Info.Info;
import br.com.jrr.apiTest.domain.RiotGames.Match.Participant.Participant;
import br.com.jrr.apiTest.domain.RiotGames.Match.Info.infoRepository.InfoRepository;
import br.com.jrr.apiTest.domain.RiotGames.Match.Repository.MatchLolRiotRepository;
import br.com.jrr.apiTest.infra.configsAPI.ApiKeyManager;
import br.com.jrr.apiTest.domain.RiotGames.Match.API.DataMatchAPI;
import br.com.jrr.apiTest.domain.RiotGames.Match.DTO.MatchDTO;
import br.com.jrr.apiTest.domain.RiotGames.Match.API.MatchRegistrationAPI;
import br.com.jrr.apiTest.service.APIConfigService.ConvertData;
import br.com.jrr.apiTest.service.APIConfigService.GetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MatchRiotWebService {

    @Autowired
    private MatchLolRiotRepository Repository;

    @Autowired
    private InfoRepository infoRepository;

    @Autowired
    private AccountRiotRepository repositoryAccount;

    private final GetData get = new GetData();
    private final ConvertData convert = new ConvertData();


    public List<MatchDTO> getMatch() {
        List<Match> matches = Repository.findAll();

        // Exibe as informações dos participantes
        matches.forEach(match -> {
            System.out.println("Match Info: " + match.getInfo());
            if (match.getInfo() != null && match.getInfo().getParticipants() != null) {
                match.getInfo().getParticipants().forEach(participant -> {
                    System.out.println("Participant: " + participant);
                });
            }
        });

        // Converte as matches para MatchDTO
        return matches.stream()
                .map(MatchDTO::fromMatch)
                .collect(Collectors.toList());
    }

    public Match getMatchById(String matchId) {
        // Busca a partida usando o matchId e retorna o DTO correspondente
        var match = Repository.findById(matchId).orElseThrow(() -> new RuntimeException("Match not found"));
        return match;
    }

    public List<Match> getmatchByPuuid(String puuid) {
        return Repository.findByAccountRiot_Id(puuid);
    }


    public MatchDTO registerByAPI(MatchRegistrationAPI data) {
        String matchId = data.matchId();

        // Obtém os dados da API Riot
        var json = get.obterDados("https://americas.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + ApiKeyManager.getApiKey());
        DataMatchAPI dataMatchAPI = convert.getDate(json, DataMatchAPI.class);

        // Cria a entidade Match a partir dos dados
        var match = new Match(dataMatchAPI);

        // Cria a entidade Info
        Info info = new Info(dataMatchAPI);
        infoRepository.save(info);  // Salva a Info primeiro

        // Cria uma nova lista de participantes e associa a Info
        List<Participant> participants = new ArrayList<>(info.getParticipants());
        participants.forEach(participant -> participant.setInfo(info));

        // Atualiza a lista de participantes na entidade Match
        match.getInfo().setParticipants(participants);

        AccountRiot user = repositoryAccount.findByPuuid("rzSO3JFUGQqzIZyf_r3Ig6Vx3-Ev-IT1nV92fIQyQVCQgkM6LyuqXljk6r47PuwcEj7ifijj5GNN-A");

       // match.setAccountRiot((Set<AccountRiot>) user);

        // Salva a entidade Match com a associação dos participantes
        Match savedMatch = Repository.save(match);

        // Retorna o MatchDTO com os dados do Match salvo
        return MatchDTO.fromMatch(savedMatch);
    }




}
