package br.com.jrr.apiTest.domain.RiotGames.Match.API;

import br.com.jrr.apiTest.domain.RiotGames.Match.Info.Info;
import br.com.jrr.apiTest.domain.RiotGames.Match.Metadado.Metadado;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DataMatchAPI

 {
     private Long id;
    @JsonProperty("metadata")
    private Metadado metadado;

     @JsonProperty("info")
     private Info info;



     public Metadado getMetadado() {
         return metadado;
     }

     public Info getInfo() {
         return info;
     }



     @Override
     public String toString() {
         return "DataMatchAPI{" +
                 "metadado=" + metadado +
                 ", info=" + info +
                 '}';
     }


 }