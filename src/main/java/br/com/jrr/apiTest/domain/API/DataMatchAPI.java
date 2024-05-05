package br.com.jrr.apiTest.domain.API;

import br.com.jrr.apiTest.domain.Match.MatchInfoEntitty;
import br.com.jrr.apiTest.domain.Match.MetadadoEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DataMatchAPI

 {
     private Long id;
    @JsonProperty("metadata")
    private MetadadoEntity metadado;

     @JsonProperty("info")
     private MatchInfoEntitty info;

     public MetadadoEntity getMetadado() {
         return metadado;
     }

     public MatchInfoEntitty getInfo() {
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