package br.com.jrr.apiTest.domain.Torneio;



import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChampionshipRepository extends JpaRepository<Championship, String> {
    @Query("SELECT new br.com.jrr.apiTest.domain.Torneio.ChampionshipWithTeamsAndMatchesDTO(c.id, t.id, m.id) " +
            "FROM Championship c " +
            "LEFT JOIN c.teams t " +
            "LEFT JOIN c.partidas m")
    List<ChampionshipWithTeamsAndMatchesDTO> findAllWithTeamsAndMatches();
}
