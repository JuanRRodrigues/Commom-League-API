package br.com.jrr.apiTest.domain.Team;


import br.com.jrr.apiTest.domain.Match.MatchEntity;
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

    @OneToOne
    @JoinColumn(name = "leader_id")
    private User leader;

    @ManyToMany(mappedBy = "teams")
    private final List<torneio> tournaments = new ArrayList<>();

    public Team(String name, String logo, String game, double saldo, int wins, int loses, boolean inGame, User leader, List<User> players) {
        this.name = name;
        this.logo = logo;
        this.game = game;
        this.saldo = saldo;
        this.wins = wins;
        this.loses = loses;
        this.inGame = inGame;
        this.leader = leader;
        this.players = new ArrayList<>();
        if (players != null) {
            this.players.addAll(players);
        }
    }


    public Team() {
    }


    public void addPlayer(User player) {
        if (this.players == null) {
            this.players = new ArrayList<>();
        }
        this.players.add(player);
    }

    public void setPlayers(List<User> players) {
        this.players = new ArrayList<>();
        if (players != null) {
            this.players.addAll(players);
        }
    }

    public void addLeader(User leader) {
        this.leader = leader;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getLogo() {
        return logo;
    }

    public void setLogo(@NotNull String logo) {
        this.logo = logo;
    }

    public @NotNull String getGame() {
        return game;
    }

    public void setGame(@NotNull String game) {
        this.game = game;
    }

    public List<User> getPlayers() {
        return players;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public List<torneio> getTournaments() {
        return tournaments;
    }
}
