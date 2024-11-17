package br.com.jrr.apiTest.domain.user.DTO;


import br.com.jrr.apiTest.enums.adress.City;
import br.com.jrr.apiTest.enums.adress.Country;
import br.com.jrr.apiTest.enums.adress.State;

public record UserGeneralEditDTO(
        String userName,
        String fullName,
        Country country,
        State state,
        City city
)
{

}