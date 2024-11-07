package br.com.jrr.apiTest.enums.controllers;

import br.com.jrr.apiTest.enums.DTO.CityDTO;
import br.com.jrr.apiTest.enums.adress.City;
import br.com.jrr.apiTest.enums.adress.State;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    /**
     * Retorna a lista de cidades para um estado específico, incluindo o nome e a descrição da cidade.
     *
     * @param state O estado para o qual você deseja obter as cidades.
     * @return Lista de objetos CityDTO com nome e descrição das cidades pertencentes ao estado fornecido.
     */
    @GetMapping("/{state}")
    public List<CityDTO> getCitiesByState(@PathVariable State state) {
        List<CityDTO> citiesInState = new ArrayList<>();

        if (state == null) {
            return citiesInState; 
        }

        for (City city : City.values()) {
            if (city.getState() == state) {
                citiesInState.add(new CityDTO(city.getName(), city.getName()));
            }
        }

        return citiesInState;
    }
}
