package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.domain.RiotGames.Match.MatchRiotWebService;
import br.com.jrr.apiTest.domain.RiotGames.Match.Repository.MatchLolRiotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TeamMonitor {

    @Autowired
    private MatchRiotWebService matchService;

    @Autowired
    private MatchLolRiotRepository matchLolRiotRepository;

    @Autowired
    private TeamRepository teamRepository;

    String captainIdBlue;
    String captainRed;
    String championshipId;

    private final Map<String, String> teamGameStatus = new HashMap<>(); // Armazena o gameId por time

    // Variável id que você mencionou
    String id;

    // Executado a cada minuto
    @Scheduled(fixedRate = 60000)
    public void monitorTeams() {
        System.out.println("Monitorando times...");

        for (String teamId : teamGameStatus.keySet()) {
            String gameId = matchService.getGameIdByTeam(teamId);

            if (gameId == null) {
                if (teamGameStatus.get(teamId) != null) {
                    // Se antes tinha gameId e agora está nulo, a partida terminou
                    System.out.println("A partida do time " + teamId + " terminou.");
                    String gameId2 = "BR1_"+gameId;
                    matchService.registerByAPI(gameId2);
                    teamGameStatus.put(teamId, null); // Atualiza para nulo
                } else {
                    // Ainda não começou a partida
                    System.out.println("O time " + teamId + " ainda não iniciou a partida.");
                }
            } else {
                if (!gameId.equals(teamGameStatus.get(teamId))) {
                    // Se o gameId mudou ou apareceu, a partida começou
                    System.out.println("O time " + teamId + " iniciou uma nova partida com gameId: " + gameId);

                    // Agora, passamos a variável id corretamente instanciada
                    matchService.getCurrentMatchById(captainIdBlue, captainRed, id);

                    teamGameStatus.put(teamId, gameId); // Atualiza o status
                } else {
                    // A partida continua ativa
                    System.out.println("O time " + teamId + " está na partida com gameId: " + gameId);
                }
            }
        }
    }

    public void addTeamToMonitor(String teamId, String id) {
        // Aqui a variável id será passada para a instância do TeamMonitor
        this.id = id;  // Armazenando a variável id
        teamGameStatus.put(teamId, null); // Adiciona o time ao monitoramento
    }

    public void removeTeamFromMonitor(String teamId) {
        teamGameStatus.remove(teamId); // Remove o time do monitoramento
    }
}
