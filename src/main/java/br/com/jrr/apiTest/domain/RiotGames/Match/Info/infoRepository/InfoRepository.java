package br.com.jrr.apiTest.domain.RiotGames.Match.Info.infoRepository;


import br.com.jrr.apiTest.domain.RiotGames.Match.Info.Info;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InfoRepository extends JpaRepository<Info, String> {
    Optional<Info> findByGameId(String gameId);

    @Query("SELECT i FROM Info i JOIN i.participants p WHERE p.puuid = :puuid")
    Page<Info> findByParticipantPuuid(String puuid, Pageable pageable);
}
