package br.com.jrr.apiTest.domain.RiotGames.Match.Repository;


import br.com.jrr.apiTest.domain.RiotGames.Match.Match;
import br.com.jrr.apiTest.domain.RiotGames.Match.Participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchLolRiotRepository extends JpaRepository<Match, String> {

    List<Match> findByAccountRiot_Id(String accountRiotId);

    Optional<Match> findByMatchId(String matchId);

}
