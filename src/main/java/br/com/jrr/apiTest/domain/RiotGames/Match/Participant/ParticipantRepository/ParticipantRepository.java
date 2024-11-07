package br.com.jrr.apiTest.domain.RiotGames.Match.Participant.ParticipantRepository;

import br.com.jrr.apiTest.domain.RiotGames.Match.Participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    // Busca todos os participantes de um match pelo info_id
    List<Participant> findByInfoId(String infoId);
}
