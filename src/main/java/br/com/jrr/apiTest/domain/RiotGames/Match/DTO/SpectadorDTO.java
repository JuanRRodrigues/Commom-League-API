package br.com.jrr.apiTest.domain.RiotGames.Match.DTO;

import br.com.jrr.apiTest.domain.RiotGames.Match.Match;
import br.com.jrr.apiTest.domain.RiotGames.Match.Participant.Participant;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SpectadorDTO(
        @JsonAlias("gameId")
        String gameId,
        @JsonAlias("gameType")
        String gameType,
        @JsonAlias("gameStartTime")
        Long gameStartTime,
        @JsonAlias("gameLength")
        Long gameLength,
        @JsonAlias("gamePlataformId")
        Long gamePlataformId,
        @JsonAlias("mapId")
        Long mapId,
        @JsonAlias("gameMode")
        String gameMode,
        @JsonAlias("participants")
        List<Participant> Participants

) {
}
