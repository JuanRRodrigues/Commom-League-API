package br.com.jrr.apiTest.domain.RiotGames.Match.Info;

import br.com.jrr.apiTest.domain.RiotGames.Match.DTO.SpectadorDTO;
import br.com.jrr.apiTest.domain.RiotGames.Match.Participant.Participant;
import br.com.jrr.apiTest.domain.RiotGames.Match.API.DataMatchAPI;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "GameInfo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonAlias("gameId")
    private String gameId;

    @JsonAlias("gameMode")
    private String gameMode;

    @JsonAlias("gameName")
    private String gameName;

    @JsonAlias("gameType")
    private String gameType;

    @JsonAlias("gameDuration")
    private String gameDuration;

    @JsonAlias("gameVersion")
    private String gameVersion;

    @JsonAlias("endOfGameResult")
    private String endOfGameResult;

    @JsonAlias("gameCreation")
    private String gameCreation;

    @JsonAlias("gameEndTimestamp")
    private String gameEndTimestamp;

    @JsonProperty("participants")
    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Participant> participants;

    public Info() {}

    // Construtor para criação a partir de DataMatchAPI
    public Info(DataMatchAPI dataMatchAPI) {
        this.gameId = dataMatchAPI.getInfo().getGameId();
        this.gameMode = dataMatchAPI.getInfo().getGameMode();
        this.gameName = dataMatchAPI.getInfo().getGameName();
        this.gameType = dataMatchAPI.getInfo().getGameType();
        this.gameDuration = dataMatchAPI.getInfo().getGameDuration();
        this.gameVersion = dataMatchAPI.getInfo().getGameVersion();
        this.endOfGameResult = dataMatchAPI.getInfo().getEndOfGameResult();
        this.gameCreation = dataMatchAPI.getInfo().getGameCreation();
        this.gameEndTimestamp = dataMatchAPI.getInfo().getGameEndTimestamp();
        this.participants = new ArrayList<>(dataMatchAPI.getInfo().getParticipants());
    }

    public Info(SpectadorDTO spectadorDTO) {
        this.gameId = spectadorDTO.gameId();
        this.gameMode = spectadorDTO.gameMode();
        this.participants = new ArrayList<>(spectadorDTO.Participants());
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(String gameDuration) {
        this.gameDuration = gameDuration;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public String getEndOfGameResult() {
        return endOfGameResult;
    }

    public void setEndOfGameResult(String endOfGameResult) {
        this.endOfGameResult = endOfGameResult;
    }

    public String getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(String gameCreation) {
        this.gameCreation = gameCreation;
    }

    public String getGameEndTimestamp() {
        return gameEndTimestamp;
    }

    public void setGameEndTimestamp(String gameEndTimestamp) {
        this.gameEndTimestamp = gameEndTimestamp;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id='" + id + '\'' +
                ", gameId='" + gameId + '\'' +
                ", gameMode='" + gameMode + '\'' +
                ", gameName='" + gameName + '\'' +
                ", gameType='" + gameType + '\'' +
                ", gameDuration='" + gameDuration + '\'' +
                ", gameVersion='" + gameVersion + '\'' +
                ", endOfGameResult='" + endOfGameResult + '\'' +
                ", gameCreation='" + gameCreation + '\'' +
                ", gameEndTimestamp='" + gameEndTimestamp + '\'' +
                ", participants=" + (participants != null ? participants.size() : 0) +

                '}';
    }
}
