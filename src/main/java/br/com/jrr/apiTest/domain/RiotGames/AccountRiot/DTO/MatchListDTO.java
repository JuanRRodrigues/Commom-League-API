package br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO;

import java.util.List;

public class MatchListDTO {
    private List<String> matchIds;  // Lista de IDs das partidas como Strings

    public MatchListDTO(List<String> matchIds) {
        this.matchIds = matchIds;
    }

    public List<String> getMatchIds() {
        return matchIds;
    }

    public void setMatchIds(List<String> matchIds) {
        this.matchIds = matchIds;
    }
}
