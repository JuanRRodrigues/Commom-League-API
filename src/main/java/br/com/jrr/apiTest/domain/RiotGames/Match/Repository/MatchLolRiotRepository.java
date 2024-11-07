package br.com.jrr.apiTest.domain.RiotGames.Match.Repository;


import br.com.jrr.apiTest.domain.RiotGames.Match.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchLolRiotRepository extends JpaRepository<Match, Long> {
}
