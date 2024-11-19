package br.com.jrr.apiTest.domain.Torneio;

import br.com.jrr.apiTest.domain.RiotGames.Match.Match;
import br.com.jrr.apiTest.domain.Team.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Championship")
@Table(name = "Championships")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Championship {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    private String ranking;

    private boolean inTorneio;

    private Double prize;

    private String img;

    private String round;  // Novo atributo
    private String championship;  // Novo atributo
    private String winner;  // Novo atributo

    @ManyToMany
    @JoinTable(
            name = "tournament_teams",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams = new ArrayList<>();

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> partidas;

    public Championship(String nome, String ranking, boolean inTorneio, List<Team> teams, List<Match> partidas, double prize, String round, String championship, String winner) {
        this.nome = nome;
        this.ranking = ranking;
        this.inTorneio = inTorneio;
        this.teams = teams;
        this.partidas = partidas;
        this.prize = prize;
        this.round = round;
        this.championship = championship;
        this.winner = winner;
    }

    public String getNome() {
        return nome;
    }

    public String getRanking() {
        return ranking;
    }

    public boolean isInTorneio() {
        return inTorneio;
    }

    public Double getPrize() {
        return prize;
    }

    public String getImg() {
        return img;
    }

    public String getRound() {
        return round;
    }

    public String getChampionship() {
        return championship;
    }

    public String getWinner() {
        return winner;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Match> getPartidas() {
        return partidas;
    }

    public Championship() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public void setInTorneio(boolean inTorneio) {
        this.inTorneio = inTorneio;
    }

    public void setPrize(Double prize) {
        this.prize = prize;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public void setChampionship(String championship) {
        this.championship = championship;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void setPartidas(List<Match> partidas) {
        this.partidas = partidas;
    }
}
