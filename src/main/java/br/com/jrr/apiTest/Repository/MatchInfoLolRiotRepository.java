package br.com.jrr.apiTest.Repository;


import br.com.jrr.apiTest.domain.Match.MatchInfoEntitty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchInfoLolRiotRepository extends JpaRepository<MatchInfoEntitty, Long> {
}
