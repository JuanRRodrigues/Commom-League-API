package br.com.jrr.apiTest.domain.Torneio;

public class ChampionshipWithTeamsAndMatchesDTO {

    private String championshipId;
    private String teamId;
    private String matchId;

    public ChampionshipWithTeamsAndMatchesDTO(String championshipId, String teamId, String matchId) {
        this.championshipId = championshipId;
        this.teamId = teamId;
        this.matchId = matchId;
    }

    public String getChampionshipId() {
        return championshipId;
    }

    public void setChampionshipId(String championshipId) {
        this.championshipId = championshipId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
