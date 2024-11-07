package br.com.jrr.apiTest.domain.RiotGames.Match.Info.infoRepository;


import br.com.jrr.apiTest.domain.RiotGames.Match.Info.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info, String> {
}
