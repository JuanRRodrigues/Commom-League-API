package br.com.jrr.apiTest.domain.RiotGames.Match.Historic.Repository;


import br.com.jrr.apiTest.domain.RiotGames.Match.Historic.Historic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchIdLolRiotRepository extends JpaRepository<Historic, Long> {
}
