package br.com.jrr.apiTest.domain.Torneio;

import br.com.jrr.apiTest.domain.Match.MatchEntity;
import br.com.jrr.apiTest.domain.Team.Team;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Entity(name = "torneio")
@Table(name = "torneios")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class torneio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    private String Ranking;

    private boolean inTorneio;

    private Double prize;

    @ManyToMany
    @JoinTable(
            name = "tournament_teams",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams = new ArrayList<>();


    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchEntity> partidas;
    public torneio(String nome, String ranking, boolean inTorneio, List<Team> teams, List<MatchEntity> partidas, double prize) {
        this.nome = nome;
        Ranking = ranking;
        this.inTorneio = inTorneio;
        this.teams = teams;
        this.partidas = partidas;
        this.prize = prize;
    }

    public torneio() {

    }
}
