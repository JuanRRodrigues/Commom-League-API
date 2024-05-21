package br.com.jrr.apiTest.domain.DTO;


import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record MatchDTO(String matchId,
                       String gameMode,

                       List<String> participants,

                       String endOfGameResult





) {



}
