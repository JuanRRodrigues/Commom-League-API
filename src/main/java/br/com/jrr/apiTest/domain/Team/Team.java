package br.com.jrr.apiTest.domain.Team;


import br.com.jrr.apiTest.domain.Torneio.torneio;
import br.com.jrr.apiTest.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Getter
@Entity(name = "team")
@Table(name = "teams")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String logo;

    @NotNull
    private String game;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> players;

    private Double saldo;

    private int wins;

    private int loses;

    private boolean inGame;

    @ManyToMany(mappedBy = "teams")
    private final List<torneio> tournaments = new ArrayList<>();

    public Team(String name, String logo, String game, Double saldo, int wins, int loses, boolean inGame) {
        this.name = name;
        this.logo = logo;
        this.game = game;
        this.saldo = saldo;
        this.wins = wins;
        this.loses = loses;
        this.inGame = inGame;
    }

    public Team() {
    }


    public void addPlayer(User player) {
        this.players.add(player);
    }




    public void setPlayers(List<User> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", game='" + game + '\'' +
                ", players=" + players +
                ", saldo=" + saldo +
                ", wins=" + wins +
                ", loses=" + loses +
                ", inGame=" + inGame +
                '}';
    }
}
