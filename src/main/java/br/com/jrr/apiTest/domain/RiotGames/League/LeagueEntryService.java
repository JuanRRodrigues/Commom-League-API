package br.com.jrr.apiTest.domain.RiotGames.League;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueEntryService {

    private final LeagueEntryRepository leagueEntryRepository;

    @Autowired
    public LeagueEntryService(LeagueEntryRepository leagueEntryRepository) {
        this.leagueEntryRepository = leagueEntryRepository;
    }

    // Método para buscar todas as LeagueEntries
    public List<LeagueEntry> getAllLeagueEntries() {
        return leagueEntryRepository.findAll();
    }

    // Método para buscar LeagueEntries por accountRiotId
    public List<LeagueEntry> getLeagueEntriesByAccountRiotId(String accountRiotId) {
        return leagueEntryRepository.findByAccountRiotId(accountRiotId);
    }

    // Método para buscar LeagueEntry por ID
    public Optional<LeagueEntry> getLeagueEntryById(String id) {
        return leagueEntryRepository.findById(id);
    }
}
