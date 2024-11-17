package br.com.jrr.apiTest.domain.RiotGames.Match;


import br.com.jrr.apiTest.domain.RiotGames.Match.Info.Info;
import br.com.jrr.apiTest.domain.RiotGames.Match.Metadado.Metadado;
import br.com.jrr.apiTest.domain.RiotGames.Match.API.DataMatchAPI;

import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.AccountRiot;
import br.com.jrr.apiTest.domain.Torneio.Torneio;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Info info;

    @OneToOne(cascade = CascadeType.ALL)
    private Metadado metadado;

    // Relacionamento Many-to-Many com AccountRiot
    @ManyToMany
    @JoinTable(
            name = "match_account",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "account_riot_id")
    )
    private Set<AccountRiot> accountRiot = new HashSet<>();



    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Torneio tournament;

    public Match(DataMatchAPI dataMatchAPI) {
        if (dataMatchAPI != null) {
            this.matchId = dataMatchAPI.getInfo().getGameId();
            this.gameMode = dataMatchAPI.getInfo().getGameMode();
            this.info = dataMatchAPI.getInfo();
            this.metadado = dataMatchAPI.getMetadado();
        }
    }

    public Match() {

    }



    @Override
    public String toString() {
        return "MatchEntity{" +
                ", matchId='" + matchId + '\'' +
                ", gameMode='" + gameMode + '\'' +
                ", info=" + info +
                ", metadado=" + metadado +
                '}';
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

    public Torneio getTournament() {
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

    public void setTournament(Torneio tournament) {
        this.tournament = tournament;
    }


}