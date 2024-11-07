package br.com.jrr.apiTest.domain.RiotGames.Match.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantDTO {

    private Long id;
    private Boolean win;
    private int championId;
    private String baronKills;
    private String riotIdGameName;
    private String quadraKills;
    private String puuid;
    private String individualPosition;
    private boolean gameEndedInSurrender;
    private int deaths;
    private int doubleKills;
    private int dragonKills;
    private int champLevel;
    private String riotIdTagline;
    private String tripleKills;

    public ParticipantDTO(Long id, Boolean win, int championId, String baronKills, String riotIdGameName, String quadraKills,
                          String puuid, String individualPosition, boolean gameEndedInSurrender, int deaths, int doubleKills,
                          int dragonKills, int champLevel, String riotIdTagline, String tripleKills) {
        this.id = id;
        this.win = win;
        this.championId = championId;
        this.baronKills = baronKills;
        this.riotIdGameName = riotIdGameName;
        this.quadraKills = quadraKills;
        this.puuid = puuid;
        this.individualPosition = individualPosition;
        this.gameEndedInSurrender = gameEndedInSurrender;
        this.deaths = deaths;
        this.doubleKills = doubleKills;
        this.dragonKills = dragonKills;
        this.champLevel = champLevel;
        this.riotIdTagline = riotIdTagline;
        this.tripleKills = tripleKills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this. id = id;
    }

    public String getTripleKills() {
        return tripleKills;
    }

    public void setTripleKills(String tripleKills) {
        this.tripleKills = tripleKills;
    }

    public String getRiotIdTagline() {
        return riotIdTagline;
    }

    public void setRiotIdTagline(String riotIdTagline) {
        this.riotIdTagline = riotIdTagline;
    }

    public int getChampLevel() {
        return champLevel;
    }

    public void setChampLevel(int champLevel) {
        this.champLevel = champLevel;
    }

    public int getDragonKills() {
        return dragonKills;
    }

    public void setDragonKills(int dragonKills) {
        this.dragonKills = dragonKills;
    }

    public int getDoubleKills() {
        return doubleKills;
    }

    public void setDoubleKills(int doubleKills) {
        this.doubleKills = doubleKills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public boolean isGameEndedInSurrender() {
        return gameEndedInSurrender;
    }

    public void setGameEndedInSurrender(boolean gameEndedInSurrender) {
        this.gameEndedInSurrender = gameEndedInSurrender;
    }

    public String getIndividualPosition() {
        return individualPosition;
    }

    public void setIndividualPosition(String individualPosition) {
        this.individualPosition = individualPosition;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getQuadraKills() {
        return quadraKills;
    }

    public void setQuadraKills(String quadraKills) {
        this.quadraKills = quadraKills;
    }

    public String getRiotIdGameName() {
        return riotIdGameName;
    }

    public void setRiotIdGameName(String riotIdGameName) {
        this.riotIdGameName = riotIdGameName;
    }

    public String getBaronKills() {
        return baronKills;
    }

    public void setBaronKills(String baronKills) {
        this.baronKills = baronKills;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }
}
