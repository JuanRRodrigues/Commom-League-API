package br.com.jrr.apiTest.domain.API;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAccountAPI(Long id,

                             @NotBlank
                           @JsonAlias("puuid")String puuid,
                             @JsonAlias("gameName")String gameName,
                             @JsonAlias("tagLine")String tagLine
                       ){
                     //   @JsonAlias("Website")String website){

}
