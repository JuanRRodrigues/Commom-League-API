package br.com.jrr.apiTest.domain.RiotGames.League;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeagueEntryRepository extends JpaRepository<LeagueEntry, Long> {

    // Busca todas as LeagueEntries associadas ao accountRiotId
    List<LeagueEntry> findByAccountRiotId(String accountRiotId);
}
