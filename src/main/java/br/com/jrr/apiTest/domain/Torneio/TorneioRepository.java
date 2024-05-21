package br.com.jrr.apiTest.domain.Torneio;


import br.com.jrr.apiTest.domain.Team.Team;
import br.com.jrr.apiTest.domain.Torneio.torneio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TorneioRepository extends JpaRepository<torneio, String> {
}
