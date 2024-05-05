package br.com.jrr.apiTest.domain.Match;

import br.com.jrr.apiTest.domain.API.DataMatchAPI;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@Entity
@Table(name = "GameInfo")
@AllArgsConstructor
@Data
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchInfoEntitty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private long id ;

    @JsonAlias("endOfGameResult")
    private String endOfGameResult;

    @JsonAlias("gameCreation")
    private String gameCreation;

    @JsonAlias("gameId")
    private String gameId;

    @JsonAlias("gameMode")
    private String gameMode;

    @JsonAlias("gameName")
    private String gameName;

    @JsonAlias("gameEndTimestamp")
    private String gameEndTimesstamp;

    @JsonAlias("gameType")
    private String gameType;

    @JsonAlias("gameVersion")
    private String gameVersion;

    @JsonAlias("gameDuration")
    private String gameDuration;


    @JsonAlias("gameEndTimestamp")
    private String getGameEndTimesstamp;

    public MatchInfoEntitty(DataMatchAPI dataMatchAPI) {
    }

    public MatchInfoEntitty() {

    }


    @Override
    public String toString() {
        return "Info{" +
                "endOfGameResult='" + endOfGameResult + '\'' +
                ", gameCreation='" + gameCreation + '\'' +
                ", gameId='" + gameId + '\'' +
                ", gameMode='" + gameMode + '\'' +
                ", gameName='" + gameName + '\'' +
                ", gameEndTimesstamp='" + gameEndTimesstamp + '\'' +
                ", gameType='" + gameType + '\'' +
                ", gameVersion='" + gameVersion + '\'' +
                ", gameDuration='" + gameDuration + '\'' +
                ", getGameEndTimesstamp='" + getGameEndTimesstamp + '\'' +
                '}';
    }



}
