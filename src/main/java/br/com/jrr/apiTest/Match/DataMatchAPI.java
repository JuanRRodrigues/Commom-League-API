package br.com.jrr.apiTest.Match;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataMatchAPI(
        @JsonProperty("id") Long id,
        @JsonProperty("gameId") String matchId,
        @JsonProperty("endOfGameResult") String endOfGameResult,
        @JsonProperty("gameCreation") Long gameCreation,
        @JsonProperty("gameEndTimestamp") Long gameEndTimestamp,
        @JsonProperty("gameMode") String gameMode,
        @JsonProperty("gameName") String gameName,
        @JsonProperty("gameDuration") Long gameDuration,
        @JsonProperty("gameStartTimestamp") Long gameStartTimestamp,
        @JsonProperty("gameType") String gameType,
        @JsonProperty("gameVersion") String gameVersion,
        @JsonProperty("mapId") String mapId
) {}
