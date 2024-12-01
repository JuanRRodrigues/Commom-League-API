package br.com.jrr.apiTest.domain.RiotGames.Match;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TournamentMonitor2 {

    @Autowired
    private MatchRiotWebService matchService;

    private final Map<String, String> playerGameStatus = new HashMap<>(); // Armazena o status do gameId por jogador

    // Executado a cada minuto
    @Scheduled(fixedRate = 60000)
    public void monitorPlayers() {
        System.out.println("Monitorando jogadores...");

        // Iterar sobre os jogadores participantes do torneio
        for (String puuid : playerGameStatus.keySet()) {
            String gameId = matchService.getGameIdByPlayer(puuid);

            if (gameId == null) {
                if (playerGameStatus.get(puuid) != null) {
                    // Se antes tinha gameId e agora está nulo, a partida terminou
                    System.out.println("A partida do jogador " + puuid + " terminou.");
                    playerGameStatus.put(puuid, null); // Atualiza o status para nulo
                } else {
                    // Ainda não começou a partida
                    System.out.println("O jogador " + puuid + " ainda não iniciou a partida.");
                }
            } else {
                if (!gameId.equals(playerGameStatus.get(puuid))) {
                    // Se o gameId mudou ou apareceu, a partida começou
                    System.out.println("O jogador " + puuid + " iniciou uma nova partida com gameId: " + gameId);
                    playerGameStatus.put(puuid, gameId); // Atualiza o status com o novo gameId
                } else {
                    // A partida continua ativa
                    System.out.println("O jogador " + puuid + " está na partida com gameId: " + gameId);
                }
            }
        }
    }

    public void addPlayerToMonitor(String puuid) {
        playerGameStatus.put(puuid, null); // Adiciona o jogador ao monitoramento
    }

    public void removePlayerFromMonitor(String puuid) {
        playerGameStatus.remove(puuid); // Remove o jogador do monitoramento
    }

    public void startTournament(List<String> playerIds) {
        System.out.println("Iniciando torneio e monitorando jogadores...");
        for (String playerId : playerIds) {
            addPlayerToMonitor(playerId); // Adiciona os jogadores para monitoramento
        }
    }

    public void endTournament() {
        System.out.println("Encerrando torneio e monitoramento...");
        playerGameStatus.clear(); // Remove todos os jogadores do monitoramento
    }
}
