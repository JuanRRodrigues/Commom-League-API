package br.com.jrr.apiTest.Repository;


import br.com.jrr.apiTest.domain.Match.MatchIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchIdLolRiotRepository extends JpaRepository<MatchIdEntity, Long> {
}
