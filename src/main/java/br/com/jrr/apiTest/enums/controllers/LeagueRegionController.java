package br.com.jrr.apiTest.enums.controllers;

import br.com.jrr.apiTest.enums.LeagueRegion;
import br.com.jrr.apiTest.enums.DTO.LeagueRegionDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/league-regions")
public class LeagueRegionController {

    // Endpoint para listar todos os LeagueRegions com DTO
    @GetMapping
    public List<LeagueRegionDTO> getAllLeagueRegions() {
        return Arrays.stream(LeagueRegion.values())
                .map(region -> new LeagueRegionDTO(region.getApiCode(), region.getDisplayName())) // Criação do DTO
                .collect(Collectors.toList());
    }

    // Endpoint para listar os LeagueRegions com nome (language) e código (name)
    @GetMapping("/names")
    public List<String> getAllRegionNames() {
        return Arrays.stream(LeagueRegion.values())
                .map(region -> region.getDisplayName()) // Retorna apenas o displayName
                .collect(Collectors.toList());
    }

    // Endpoint para listar LeagueRegions com ID e nome
    @GetMapping("/id-names")
    public List<String> getRegionIdAndNames() {
        return Arrays.stream(LeagueRegion.values())
                .map(region -> region.getId() + " - " + region.getDisplayName()) // ID + Nome
                .collect(Collectors.toList());
    }

    // Endpoint para obter LeagueRegion por ID usando DTO
    @GetMapping("/{id}")
    public LeagueRegionDTO getLeagueRegionById(@PathVariable int id) {
        return Arrays.stream(LeagueRegion.values())
                .filter(region -> region.getId() == id)
                .findFirst()
                .map(region -> new LeagueRegionDTO(region.getApiCode(), region.getDisplayName())) // Retorna o DTO
                .orElseThrow(() -> new IllegalArgumentException("Region not found"));
    }

    // Endpoint para obter LeagueRegion por código da API (usando DTO)
    @GetMapping("/api-code/{apiCode}")
    public LeagueRegionDTO getLeagueRegionByApiCode(@PathVariable String apiCode) {
        return Arrays.stream(LeagueRegion.values())
                .filter(region -> region.getApiCode().equalsIgnoreCase(apiCode))
                .findFirst()
                .map(region -> new LeagueRegionDTO(region.getApiCode(), region.getDisplayName())) // Retorna o DTO
                .orElseThrow(() -> new IllegalArgumentException("Region not found"));
    }
}
