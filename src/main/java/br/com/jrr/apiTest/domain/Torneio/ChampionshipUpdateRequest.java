package br.com.jrr.apiTest.domain.Torneio;

import br.com.jrr.apiTest.domain.RiotGames.Match.Match;
import br.com.jrr.apiTest.domain.Team.Team;

import java.util.List;

public class ChampionshipUpdateRequest {

    private String nome;
    private String ranking;
    private boolean inTorneio;
    private double prize;
    private String img;
    private String round;
    private String championship;
    private String winner;
    private List<Team> Teams;  // Lista de times
    private List<Match> partidas;  // Lista de partidas

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public boolean isInTorneio() {
        return inTorneio;
    }

    public void setInTorneio(boolean inTorneio) {
        this.inTorneio = inTorneio;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getChampionship() {
        return championship;
    }

    public void setChampionship(String championship) {
        this.championship = championship;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<Team> getTeam() {
        return Teams;
    }

    public void setTimes(List<Team> Teams) {
        this.Teams = Teams;
    }

    public List<Match> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Match> partidas) {
        this.partidas = partidas;
    }
}
