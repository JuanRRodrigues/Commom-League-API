package br.com.jrr.apiTest.domain.Torneio;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/championships")
public class ChampionshipController {

    private final ChampionshipService championshipService;

    public ChampionshipController(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }

    @GetMapping
    public List<Championship> getAllChampionships() {
        return championshipService.getAllChampionships();
    }

    @PostMapping("/populate")
    public List<Championship> populateChampionships() {
        List<Championship> championships = Arrays.asList(
                new Championship("Worlds 2024", "Tier 1", true, null, null, 500000.00, "Finals", "International", "Team A"),
                new Championship("Spring Split 2024", "Tier 2", true, null, null, 200000.00, "Playoffs", "Regional", "Team B"),
                new Championship("Summer Split 2024", "Tier 2", true, null, null, 300000.00, "Semifinals", "Regional", "Team C"),
                new Championship("MSI 2024", "Tier 1", true, null, null, 400000.00, "Finals", "International", "Team D"),
                new Championship("All-Stars 2024", "Exhibition", false, null, null, 100000.00, "Group Stage", "Exhibition", "N/A")
        );

        return championshipService.saveAll(championships);
    }
}
