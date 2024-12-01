package br.com.jrr.apiTest.domain.RiotGames.Match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TournamentMonitor {

    @Autowired
    private MatchRiotWebService matchService; // Um serviço para encapsular o método abaixo

    private String captainIdBlue = "blueCaptainId"; // Substitua pelo valor real ou passe dinamicamente
    private String captainIdRed = "redCaptainId";   // Substitua pelo valor real ou passe dinamicamente
    private String championshipId = "championshipId"; // Substitua pelo valor real ou passe dinamicamente

   // @Scheduled(fixedRate = 60000) // Executa a cada 1 minuto
    public void monitorMatch() {
        try {
            matchService.getCurrentMatchById(captainIdBlue, captainIdRed, championshipId);
        } catch (Exception e) {
            System.err.println("Erro ao monitorar a partida: " + e.getMessage());
        }
    }
}
