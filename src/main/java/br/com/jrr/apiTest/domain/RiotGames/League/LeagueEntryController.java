package br.com.jrr.apiTest.domain.RiotGames.League;

import br.com.jrr.apiTest.domain.RiotGames.League.LeagueEntry;
import br.com.jrr.apiTest.domain.RiotGames.League.LeagueEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leagueEntries")
public class LeagueEntryController {

    private final LeagueEntryService leagueEntryService;

    @Autowired
    public LeagueEntryController(LeagueEntryService leagueEntryService) {
        this.leagueEntryService = leagueEntryService;
    }

    // Endpoint para buscar todas as LeagueEntries
    @GetMapping
    public List<LeagueEntry> getAllLeagueEntries() {
        return leagueEntryService.getAllLeagueEntries();
    }

    // Endpoint para buscar LeagueEntries por accountRiotId
    @GetMapping("/byAccountRiotId/{accountRiotId}")
    public List<LeagueEntry> getLeagueEntriesByAccountRiotId(@PathVariable String accountRiotId) {
        return leagueEntryService.getLeagueEntriesByAccountRiotId(accountRiotId);
    }

    // Endpoint para buscar LeagueEntry por id
    @GetMapping("/{id}")
    public Optional<LeagueEntry> getLeagueEntryById(@PathVariable Long id) {
        return leagueEntryService.getLeagueEntryById(id);
    }
}
