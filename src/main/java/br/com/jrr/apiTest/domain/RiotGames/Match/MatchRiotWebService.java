package br.com.jrr.apiTest.domain.RiotGames.Match;



import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.AccountRiot;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.AccountRiotRepository;
import br.com.jrr.apiTest.domain.RiotGames.Match.DTO.SpectadorDTO;
import br.com.jrr.apiTest.domain.RiotGames.Match.Info.Info;
import br.com.jrr.apiTest.domain.RiotGames.Match.Participant.Participant;
import br.com.jrr.apiTest.domain.RiotGames.Match.Info.infoRepository.InfoRepository;
import br.com.jrr.apiTest.domain.RiotGames.Match.Repository.MatchLolRiotRepository;
import br.com.jrr.apiTest.domain.Team.Team;
import br.com.jrr.apiTest.domain.Team.TeamRepository;
import br.com.jrr.apiTest.domain.Torneio.ChampionshipRepository;
import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.infra.configsAPI.ApiKeyManager;
import br.com.jrr.apiTest.domain.RiotGames.Match.API.DataMatchAPI;
import br.com.jrr.apiTest.domain.RiotGames.Match.DTO.MatchDTO;
import br.com.jrr.apiTest.service.APIConfigService.ConvertData;
import br.com.jrr.apiTest.service.APIConfigService.GetData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ChampionshipRepository championshipRepository;

    @Autowired
    private final TeamRepository teamRepository;



    private final GetData get = new GetData();
    private final ConvertData convert = new ConvertData();

    public MatchRiotWebService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


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

    public String getGameIdByPlayer(String playerId) {
        try {
            String url = "https://br1.api.riotgames.com/lol/spectator/v5/active-games/by-summoner/" + playerId + "?api_key=" + ApiKeyManager.getApiKey();
            String response = get.obterDados(url);

            SpectadorDTO spectador = convert.getDate(response, SpectadorDTO.class);

            // Retorna o gameId (pode ser nulo se o jogador não estiver em partida)
            return spectador.gameId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public ResponseEntity<String> getCurrentMatchById(String captainIdBlue, String captainRed, String id) {
        // Busca o Match diretamente
        Match match = Repository.findById(id).orElse(null);
        System.out.println(id);
        System.out.println("captain blue: " + captainIdBlue);

        // Verifica se o Match foi encontrado
        if (match == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match não encontrado.");
        }

        // Verifica se o Match já foi processado (se o gameId já está atribuído)
        if (match.getMatchId() != null) {
            System.out.println("Esta partida já foi processada.");
            return ResponseEntity.ok("A partida já foi processada.");
        }

        // Chama as APIs para obter dados dos times
        var jsonTeam1 = get.obterDados("https://br1.api.riotgames.com/lol/spectator/v5/active-games/by-summoner/" + "_-qSsd2yeqbtiQiff6UMa--BOTxqdvOfKgTOIPdvNwxEpiA7LR1TpwCXOZ1MoqEfEGdINtbn5I20CQ" + "?api_key=" + ApiKeyManager.getApiKey());
        var jsonTeam2 = get.obterDados("https://br1.api.riotgames.com/lol/spectator/v5/active-games/by-summoner/" + "_-qSsd2yeqbtiQiff6UMa--BOTxqdvOfKgTOIPdvNwxEpiA7LR1TpwCXOZ1MoqEfEGdINtbn5I20CQ" + "?api_key=" + ApiKeyManager.getApiKey());

        // Verificação de erro na resposta dos times
        if (isErrorResponse(jsonTeam1)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao obter dados para o time 1: " + jsonTeam1);
        }
        if (isErrorResponse(jsonTeam2)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao obter dados para o time 2: " + jsonTeam2);
        }

        try {
            // Converte as respostas para SpectadorDTO
            SpectadorDTO team1 = convert.getDate(jsonTeam1, SpectadorDTO.class);
            SpectadorDTO team2 = convert.getDate(jsonTeam2, SpectadorDTO.class);

            // Verifica se gameId está nulo
            if (team1.gameId() == null) {
                return ResponseEntity.status(HttpStatus.OK).body("O jogador 1 não está em partida no momento.");
            }

            if (team2.gameId() == null) {
                return ResponseEntity.status(HttpStatus.OK).body("O jogador 2 não está em partida no momento.");
            }

            // Verifica se os dois times estão no mesmo jogo
            if (team1.gameId().equals(team2.gameId())) {
                System.out.println("Eles estão na mesma partida.");

                // Atualiza e salva o Match
                match.setMatchId(team1.gameId());
                match.setGameMode(team1.gameMode());
                match.setInfo(new Info(team1));

                // Atualiza participantes e associações
                List<Participant> participants = new ArrayList<>(match.getInfo().getParticipants());
                participants.forEach(participant -> participant.setInfo(match.getInfo()));
                match.getInfo().setParticipants(participants);

                // Salva o match no repositório
                Repository.save(match);
                System.out.println("Match atualizado e salvo.");

                return ResponseEntity.ok("Times processados com sucesso.");
            } else {
                System.out.println("Eles não estão na mesma partida.");
                return ResponseEntity.ok("Os jogadores não estão na mesma partida.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao tentar converter JSON para SpectadorDTO: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar dados dos times.");
        }
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

            System.out.println(dataMatchAPI);
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

    public MatchDTO FinishedMatch(String matchID) {

        // Obtém os dados da API Riot
        var json = get.obterDados("https://americas.api.riotgames.com/lol/match/v5/matches/" + matchID + "?api_key=" + ApiKeyManager.getApiKey());
        DataMatchAPI dataMatchAPI = convert.getDate(json, DataMatchAPI.class);

        // Verifica se o Match já existe no banco de dados com o matchID
        Optional<Match> existingMatch = Repository.findByMatchId(matchID);
        if (existingMatch.isPresent()) {
            // Se o match já existir, atualiza o registro com os dados finais da partida
            Match match = existingMatch.get();

            // Atualizando as informações
            match.setInfo(new Info(dataMatchAPI));  // Atualiza a info

            // Verifica o vencedor
          //  String winner = dataMatchAPI.getWinner();
           // match.setWinner(winner); // Atualiza o vencedor da partida

            // Atualiza participantes e as associações de AccountRiot
            List<Participant> participants = new ArrayList<>(match.getInfo().getParticipants());
            participants.forEach(participant -> participant.setInfo(match.getInfo()));
            match.getInfo().setParticipants(participants);

            // Salva o Match atualizado
            Match savedMatch = Repository.save(match);

            // Retorna o MatchDTO com os dados do Match atualizado
            return MatchDTO.fromMatch(savedMatch);

        } else {
            // Se o match não existir, cria um novo e associa as entidades normalmente
            Match match = new Match(dataMatchAPI);
            Info info = new Info(dataMatchAPI);
            infoRepository.save(info);  // Salva a Info primeiro

            // Cria uma nova lista de participantes e associa a Info
            List<Participant> participants = new ArrayList<>(info.getParticipants());
            participants.forEach(participant -> participant.setInfo(info));
            match.getInfo().setParticipants(participants);

            // Criação de Set para armazenar as contas
            Set<AccountRiot> accounts = new HashSet<>();
            AccountRiot user = repositoryAccount.findByPuuid("pRXauqTqgHKQ-XrRvKyriGk9dthO9pwItVgG4GJV80t8Atj96YACNHxu7ccT_R0ALHVT13CMoKEzGw");
            if (user != null) {
                accounts.add(user);
            }

            // Associando a conta ao match
            match.setAccountRiot(accounts);

            // Salva a entidade Match com as novas associações
            Match savedMatch = Repository.save(match);

            // Retorna o MatchDTO com os dados do Match salvo
            return MatchDTO.fromMatch(savedMatch);
        }
    }


    public String getGameIdByTeam(String teamId) {
        try {
            // Exemplo: usa o ID do capitão para obter o estado da partida
            User captainId = teamRepository.findById(teamId)
                    .map(Team::getLeader)
                    .orElse(null);

            if (captainId == null) {
                System.out.println("Capitão não encontrado para o time: " + teamId);
                return null;
            }

            String url = "https://br1.api.riotgames.com/lol/spectator/v5/active-games/by-summoner/" + captainId.getAccountRiot().getPuuid() + "?api_key=" + ApiKeyManager.getApiKey();
            String response = get.obterDados(url);

            SpectadorDTO spectador = convert.getDate(response, SpectadorDTO.class);

            return spectador.gameId(); // Retorna o gameId ou null
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isErrorResponse(String jsonResponse) {
        // Verifica se a resposta contém um erro (status 404 ou qualquer outro status de erro)
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            if (rootNode.has("status") && rootNode.get("status").has("status_code")) {
                int statusCode = rootNode.get("status").get("status_code").asInt();
                if (statusCode != 200) { // Código de sucesso da API é 200
                    return true;
                }
            }
        } catch (JsonProcessingException e) {
            // Caso não consiga parsear o JSON, assume que é erro
            return true;
        }
        return false;
    }
}
