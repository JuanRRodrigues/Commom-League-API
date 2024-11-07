package br.com.jrr.apiTest.domain.user.DTO;

import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.AccountRiot;
import br.com.jrr.apiTest.domain.RiotGames.Match.DTO.MatchDTO;

import java.util.List;

public record DetailAccountDTO(String id, String puuid, String gameName, String tagLine) {

    // Construtor que aceita AccountRiot
    public DetailAccountDTO(AccountRiot accountRiot) {
        this(
                accountRiot.getId(),
                accountRiot.getPuuid(),
                accountRiot.getGameName(),
                accountRiot.getTagLine()
               // MatchDTO.fromMatchList(accountRiot.getMatchList()) // Aqui deve retornar uma lista de MatchDTO
        );
    }
}
