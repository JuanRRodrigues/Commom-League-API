package br.com.jrr.apiTest.domain.user.DTO;

import br.com.jrr.apiTest.domain.RiotGames.Match.Match;

import java.util.List;


public record AccountMatchRiotDTO(String id,
                                  String puuid,
                                  String gameName,
                                  String tagLine,
                                  List<Match>idMatchList

                             ) {



}
