package br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO;

public class AssociacaoContaDTO {

    private String id;  // Novo campo para armazenar o ID da conta
    private String gameName;
    private String tagLine;
    private String profileIconId;
    private String summonerLevel;
    private String wins;
    private String loses;
    private String elo;  // Correção para o tipo de rank (elo)
    private String queueType;  // Tipo de fila de rankeado
    private String points;  // Pontos de liga (LP)

    // Construtor
    public AssociacaoContaDTO(String id, String gameName, String tagLine, String profileIconId, String summonerLevel,
                              String wins, String loses, String elo, String queueType, String points) {
        this.id = id;  // Atribui o ID
        this.gameName = gameName;
        this.tagLine = tagLine;
        this.profileIconId = profileIconId;
        this.summonerLevel = summonerLevel;
        this.wins = wins;
        this.loses = loses;
        this.elo = elo;
        this.queueType = queueType;
        this.points = points;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(String profileIconId) {
        this.profileIconId = profileIconId;
    }

    public String getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(String summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getLoses() {
        return loses;
    }

    public void setLoses(String loses) {
        this.loses = loses;
    }

    public String getElo() {
        return elo;
    }

    public void setElo(String elo) {
        this.elo = elo;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
