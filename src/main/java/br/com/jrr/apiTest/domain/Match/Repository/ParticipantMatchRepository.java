package br.com.jrr.apiTest.domain.Match.Repository;


import br.com.jrr.apiTest.domain.Match.MatchEntity;
import br.com.jrr.apiTest.domain.Match.ParticipantsMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantMatchRepository extends JpaRepository<ParticipantsMatch, String> {
}
