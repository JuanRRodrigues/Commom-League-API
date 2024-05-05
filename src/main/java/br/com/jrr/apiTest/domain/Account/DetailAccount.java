package br.com.jrr.apiTest.domain.Account;

import java.util.List;
import java.util.UUID;

public record DetailAccount(UUID id, String puuid, String gameName, String tagLine, List<String> idMatchList ) {


    public DetailAccount(AccountRiot accountRiot){
        this(accountRiot.getId(),  accountRiot.getPuuid(),  accountRiot.getGameName(),accountRiot.getTagLine() , accountRiot.getIdMatchList());
    }

}
