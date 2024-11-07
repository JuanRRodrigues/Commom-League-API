package br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO;

import br.com.jrr.apiTest.domain.RiotGames.Match.Match;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.AccountRiot;

import java.util.List;

public record DetailAccountDTO(String id, String puuid, String gameName, String tagLine, String summonerLevel, String profileIcon ) {


    public DetailAccountDTO(AccountRiot accountRiot){
        this(accountRiot.getId(),  accountRiot.getPuuid(),  accountRiot.getGameName(),accountRiot.getTagLine(), accountRiot.getSummonerLevel(), accountRiot.getProfileIconId());
    }

}
