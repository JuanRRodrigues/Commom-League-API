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
@Entity(name = "torneio")
@Table(name = "torneios")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Torneio {

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

    public Torneio(String nome, String ranking, boolean inTorneio, List<Team> teams, List<Match> partidas, double prize, String round, String championship, String winner) {
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

    public Torneio() {
    }
}
