package br.com.jrr.apiTest.domain.DTO;

import java.util.List;
import java.util.UUID;


public record AccountMatchRiotDTO(String id,
                                  String puuid,
                                  String gameName,
                                  String tagLine,
                                  List<String>idMatchList

                             ) {



}
