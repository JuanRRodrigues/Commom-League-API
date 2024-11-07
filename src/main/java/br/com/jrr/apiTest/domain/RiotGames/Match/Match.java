package br.com.jrr.apiTest.domain.RiotGames.Match;


import br.com.jrr.apiTest.domain.RiotGames.Match.Info.Info;
import br.com.jrr.apiTest.domain.RiotGames.Match.Metadado.Metadado;
import br.com.jrr.apiTest.domain.RiotGames.Match.API.DataMatchAPI;

import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.AccountRiot;
import br.com.jrr.apiTest.domain.Torneio.Torneio;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "GameMatch")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String matchId;
    private String gameMode;

    @OneToOne(cascade = CascadeType.ALL)
    private Info info;

    @OneToOne(cascade = CascadeType.ALL)
    private Metadado metadado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_riot_id")
    private AccountRiot accountRiot;

    public AccountRiot getAccountRiot() {
        return accountRiot;
    }

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
                "id=" + id +
                ", matchId='" + matchId + '\'' +
                ", gameMode='" + gameMode + '\'' +
                ", info=" + info +
                ", metadado=" + metadado +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountRiot(AccountRiot accountRiot) {
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