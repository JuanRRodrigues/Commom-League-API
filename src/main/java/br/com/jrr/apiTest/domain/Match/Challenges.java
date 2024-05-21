package br.com.jrr.apiTest.domain.Match;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

public class Challenges {

    @Getter
    @JsonAlias("kills")
    private String kills;

    @Getter
    @JsonAlias("minionsKilled")
    private String minionsKilled;

    @Getter
    @JsonAlias("win")
    private Boolean win;

    @Getter
    @JsonAlias("deathsByEnemyChamps")
    private int death;



}
