package br.com.jrr.apiTest.Repository;


import br.com.jrr.apiTest.domain.Match.MetadadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchMedadooLolRiotRepository extends JpaRepository<MetadadoEntity, Long> {
}
