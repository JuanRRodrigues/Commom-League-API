package br.com.jrr.apiTest.enums.controllers;

import br.com.jrr.apiTest.enums.DTO.CountryDTO;
import br.com.jrr.apiTest.enums.adress.Country;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @GetMapping
    public List<CountryDTO> getAllCountries() {
        List<CountryDTO> countries = new ArrayList<>();

        for (Country country : Country.values()) {
            countries.add(new CountryDTO(country.getName(), country.getLanguage().toString()));
        }

        return countries;
    }
    
    @GetMapping("/{countryName}")
    public CountryDTO getCountryByName(@PathVariable String countryName) {
        for (Country country : Country.values()) {
            if (country.getName().equalsIgnoreCase(countryName)) {
                return new CountryDTO(country.getName(), country.getLanguage().toString());
            }
        }
        throw new IllegalArgumentException("Country not found");
    }
}
