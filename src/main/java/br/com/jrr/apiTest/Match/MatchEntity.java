package br.com.jrr.apiTest.Match;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "GameMatch")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type_entity", discriminatorType = DiscriminatorType.STRING)


public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long id; // Changed to Long for ID generation

    private String matchId;

    private String endOfGameResult;
    private String gameCreation;
    private String gameDuration;
    private String gameEndTimestamp;
    private String gameMode;
    private String gameName;
    private String gameStartTimestamp;
    private String gameType;
    private String gameVersion;
    private String mapId;


    public MatchEntity(DataMatchAPI dataMatchAPI) {
        this.matchId = dataMatchAPI.matchId();
        this.endOfGameResult = dataMatchAPI.endOfGameResult();
        this.gameCreation = String.valueOf(dataMatchAPI.gameCreation());
        this.gameDuration = String.valueOf(dataMatchAPI.gameDuration());
        this.gameEndTimestamp = String.valueOf(dataMatchAPI.gameEndTimestamp());
        this.gameMode = dataMatchAPI.gameMode();
        this.gameName = dataMatchAPI.gameName();
        this.gameStartTimestamp = String.valueOf(dataMatchAPI.gameStartTimestamp());
        this.gameType = dataMatchAPI.gameType();
        this.gameVersion = dataMatchAPI.gameVersion();
        this.mapId = dataMatchAPI.mapId();
    }

    public MatchEntity() {

    }

    @Override
    public String toString() {
        return "MatchEntity{" +
                "id=" + id +
                ", matchId='" + matchId + '\'' +
                ", endOfGameResult='" + endOfGameResult + '\'' +
                ", gameCreation=" + gameCreation +
                ", gameDuration=" + gameDuration +
                ", gameEndTimestamp=" + gameEndTimestamp +
                ", gameMode='" + gameMode + '\'' +
                ", gameName='" + gameName + '\'' +
                ", gameStartTimestamp=" + gameStartTimestamp +
                ", gameType='" + gameType + '\'' +
                ", gameVersion='" + gameVersion + '\'' +
                ", mapId=" + mapId +
                '}';
    }


    //   private List<Participant> participants;

}
