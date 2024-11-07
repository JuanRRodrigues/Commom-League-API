package br.com.jrr.apiTest.domain.RiotGames.Match.Metadado.Repository;


import br.com.jrr.apiTest.domain.RiotGames.Match.Metadado.Metadado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetadadoRepository extends JpaRepository<Metadado, Long> {
}
