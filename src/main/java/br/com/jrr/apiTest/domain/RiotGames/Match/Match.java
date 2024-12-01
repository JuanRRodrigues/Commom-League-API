package br.com.jrr.apiTest.domain.RiotGames.Match;


import br.com.jrr.apiTest.domain.RiotGames.Match.DTO.SpectadorDTO;
import br.com.jrr.apiTest.domain.RiotGames.Match.Info.Info;
import br.com.jrr.apiTest.domain.RiotGames.Match.Metadado.Metadado;
import br.com.jrr.apiTest.domain.RiotGames.Match.API.DataMatchAPI;

import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.AccountRiot;
import br.com.jrr.apiTest.domain.Team.Team;
import br.com.jrr.apiTest.domain.Torneio.Championship;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Table(name = "GameMatch")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonAlias("matchId")
    private String matchId;

    private String gameMode;

    @ManyToOne
    private Team team1;

    @ManyToOne
    private Team team2;

    @OneToOne(cascade = CascadeType.ALL)
    private Info info;

    @OneToOne(cascade = CascadeType.ALL)
    private Metadado metadado;

    @JsonIgnore
    // Relacionamento Many-to-Many com AccountRiot
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "match_account",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "account_riot_id")
    )
    private Set<AccountRiot> accountRiot = new HashSet<>();




    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "championship_id")
    private Championship tournament;

    public Match(DataMatchAPI dataMatchAPI) {
        if (dataMatchAPI != null) {
            this.matchId = dataMatchAPI.getInfo().getGameId();
            this.gameMode = dataMatchAPI.getInfo().getGameMode();
            this.info = dataMatchAPI.getInfo();
            this.metadado = dataMatchAPI.getMetadado();
        }
    }

    public Match(SpectadorDTO spectadorDTO) {
        if (spectadorDTO != null) {
            this.matchId = spectadorDTO.gameId();
            this.gameMode = spectadorDTO.gameType();
        }
    }

    public Match() {

    }


    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", matchId='" + matchId + '\'' +
                ", gameMode='" + gameMode + '\'' +
                ", team1=" + team1.getName() +  // Exibe apenas o nome do time1
                ", team2=" + team2.getName() +  // Exibe apenas o nome do time2
                ", info=" + info +
                ", metadado=" + metadado +
                ", accountRiot=" + accountRiot +
                ", tournament=" + tournament +
                '}';
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public Set<AccountRiot> getAccountRiot() {
        return accountRiot;
    }

    public void setAccountRiot(Set<AccountRiot> accountRiot) {
        this.accountRiot = accountRiot;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public Info getInfo() {
        return info;
    }

    public Metadado getMetadado() {
        return metadado;
    }

    public Championship getTournament() {
        return tournament;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public void setMetadado(Metadado metadado) {
        this.metadado = metadado;
    }

    public void setTournament(Championship tournament) {
        this.tournament = tournament;
    }

    public String getId() {
        return id;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }
}