package br.com.jrr.apiTest.domain.API;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DataMediaAPI(Long id,
                           @NotBlank
                           @JsonAlias("gameName") String gameName,
                           @NotBlank
                           @JsonAlias("puuid")String puuid,
                           @NotBlank
                           @JsonAlias("tagLine")String tagLine
                       ){
                     //   @JsonAlias("Website")String website){

}
