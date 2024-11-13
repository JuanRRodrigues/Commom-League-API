package br.com.jrr.apiTest.domain.RiotGames.AccountRiot.API;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAccountAPI(String id,


                             //Data 1 - Public
                             @NotBlank
                             @JsonAlias("puuid")String puuid,
                             @NotBlank
                             @JsonAlias("gameName")String gameName,
                             @NotBlank
                             @JsonAlias("tagLine")String tagLine,

                             //Data 2 - PUUID
                             @NotBlank
                             @JsonAlias("accountId")String accountId,
                             @NotBlank
                             @JsonAlias("id")String idRiot,
                             @NotBlank
                             @JsonAlias("revisionDate")String revisionDate,
                             @NotBlank
                             @JsonAlias("profileIconId")String profileIconId,
                             @NotBlank
                             @JsonAlias("summonerLevel")String summonerLevel,
                             //Data 3 - PUUID
                             @JsonProperty("idMatch")List<String> idMatchList,

                             //Data 4 - ACCOUNTID
                             @JsonAlias("queueType")String queueType,
                             @JsonAlias("tier")String tier,
                             @JsonAlias("rank")String elo,
                             @JsonAlias("leaguePoints")String leaguePoints,
                             @JsonAlias("wins")String wins,
                             @JsonAlias("losses")String losses








){


    //   @JsonAlias("Website")String website){

}
