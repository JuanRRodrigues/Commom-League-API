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

import java.util.*;
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
        // Busca as partidas associadas ao puuid e carrega os participantes
        List<Match> matches = Repository.findByAccountRiot_Id(puuid);

        // Exibe as informações dos participantes
        matches.forEach(match -> {
            System.out.println("Match Info: " + match.getInfo());
            if (match.getInfo() != null && match.getInfo().getParticipants() != null) {
                match.getInfo().getParticipants().forEach(participant -> {
                    System.out.println("Participant: " + participant);
                });
            }
        });

        // Retorna todas as partidas diretamente
        return matches;
    }


    public MatchDTO registerByAPI(String matchID) {

        // Obtém os dados da API Riot
        var json = get.obterDados("https://americas.api.riotgames.com/lol/match/v5/matches/" + matchID + "?api_key=" + ApiKeyManager.getApiKey());
        DataMatchAPI dataMatchAPI = convert.getDate(json, DataMatchAPI.class);

        // Verifica se o Match já existe no banco de dados com o matchID
        Optional<Match> existingMatch = Repository.findByMatchId(matchID);
        if (existingMatch.isPresent()) {
            // Se o match já existir, pode-se optar por atualizar o registro ou retornar uma resposta indicando que o match já foi registrado
            Match match = existingMatch.get();

            // Atualizando as informações, se necessário
            match.setInfo(new Info(dataMatchAPI));  // Atualiza a info

            // Atualiza participantes e as associações de AccountRiot
            List<Participant> participants = new ArrayList<>(match.getInfo().getParticipants());
            participants.forEach(participant -> participant.setInfo(match.getInfo()));
            match.getInfo().setParticipants(participants);

            // Verifica se a conta já está associada ao Match, se não, adiciona
            Set<AccountRiot> accounts = match.getAccountRiot();
            AccountRiot user = repositoryAccount.findByPuuid("pRXauqTqgHKQ-XrRvKyriGk9dthO9pwItVgG4GJV80t8Atj96YACNHxu7ccT_R0ALHVT13CMoKEzGw");

            if (user != null) {
                accounts.add(user);
            }

            // Atualiza a associação com AccountRiot
            match.setAccountRiot(accounts);

            // Salva o Match atualizado
            Match savedMatch = Repository.save(match);

            // Retorna o MatchDTO com os dados do Match atualizado
            return MatchDTO.fromMatch(savedMatch);

        } else {
            // Se o match não existir, cria e associa as entidades normalmente

            // Cria a entidade Match a partir dos dados
            Match match = new Match(dataMatchAPI);

            // Cria a entidade Info e associa ao Match
            Info info = new Info(dataMatchAPI);
            infoRepository.save(info);  // Salva a Info primeiro

            // Cria uma nova lista de participantes e associa a Info
            List<Participant> participants = new ArrayList<>(info.getParticipants());
            participants.forEach(participant -> participant.setInfo(info));

            // Atualiza a lista de participantes na entidade Match
            match.getInfo().setParticipants(participants);

            // Criando um Set para armazenar as contas dos jogadores
            Set<AccountRiot> accounts = new HashSet<>();

            AccountRiot user = repositoryAccount.findByPuuid("pRXauqTqgHKQ-XrRvKyriGk9dthO9pwItVgG4GJV80t8Atj96YACNHxu7ccT_R0ALHVT13CMoKEzGw");

            // Adicionando a conta ao Set
            if (user != null) {
                accounts.add(user);
            }

            // Associando o Set de AccountRiot ao Match
            match.setAccountRiot(accounts);

            // Salva a entidade Match com a associação dos participantes
            Match savedMatch = Repository.save(match);

            // Retorna o MatchDTO com os dados do Match salvo
            return MatchDTO.fromMatch(savedMatch);
        }
    }





}
