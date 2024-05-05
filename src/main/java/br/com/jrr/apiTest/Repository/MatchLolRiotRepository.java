package br.com.jrr.apiTest.Repository;


import br.com.jrr.apiTest.domain.Match.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchLolRiotRepository extends JpaRepository<MatchEntity, Long> {
}
