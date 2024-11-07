package br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO;

import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.AccountRiot;
import br.com.jrr.apiTest.domain.RiotGames.Match.DTO.MatchDTO;

import java.util.List;
import java.util.stream.Collectors;

public record AccountRiotDTO(
        String id,
        String puuid,
        String gameName,
        String tagLine,
        String accountId,
        String idRiot,
        String profileIconId,
        String revisionDate,
        String summonerLevel
     //   List<br.com.jrr.apiTest.domain.RiotGames.Match.Match> matchList
) {


    public static AccountRiotDTO fromAccountRiot(AccountRiot accountRiot) {
        if (accountRiot == null) {
            return null;
        }

        return new AccountRiotDTO(
                accountRiot.getId(),
                accountRiot.getPuuid(),
                accountRiot.getGameName(),
                accountRiot.getTagLine(),
                accountRiot.getAccountId(),
                accountRiot.getIdRiot(),
                accountRiot.getProfileIconId(),
                accountRiot.getRevisionDate(),
                accountRiot.getSummonerLevel()
              //  accountRiot.getMatchList() // Supondo que este método retorne List<Match>
              //          .stream()
              //          .map(MatchDTO::fromMatch) // Converter cada Match em MatchDTO
               //         .collect(Collectors.toList()) // Coletar em uma lista de MatchDTO
        );
    }
}
