package br.com.jrr.apiTest.domain.user.DTO;


import br.com.jrr.apiTest.enums.Language;
import br.com.jrr.apiTest.enums.LeagueRegion;

public record UserLanguageEditDTO(
        Language language,
        LeagueRegion leagueRegion
)
{

}