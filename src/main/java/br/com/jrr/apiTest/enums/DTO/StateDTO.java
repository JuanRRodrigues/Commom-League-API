package br.com.jrr.apiTest.enums.DTO;

import br.com.jrr.apiTest.enums.adress.Country;

public class StateDTO {
    private String name;
    private Country country;

    public StateDTO(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
