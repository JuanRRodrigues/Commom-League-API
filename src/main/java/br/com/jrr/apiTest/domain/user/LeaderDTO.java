package br.com.jrr.apiTest.domain.user;

import br.com.jrr.apiTest.domain.DTO.AccountRiotDTO;

import java.time.LocalDate;

public record LeaderDTO(
        String id,
        String login,
        String fullName
        ) {

}

