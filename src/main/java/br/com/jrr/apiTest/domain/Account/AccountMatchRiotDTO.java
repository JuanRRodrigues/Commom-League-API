package br.com.jrr.apiTest.domain.Account;

import java.util.List;
import java.util.UUID;


public record AccountMatchRiotDTO(UUID id,
                                  String puuid,
                                  String gameName,
                                  String tagLine,
                                  List<String>idMatchList

                             ) {



}
