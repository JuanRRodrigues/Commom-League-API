package br.com.jrr.apiTest.domain.RiotGames.Match.Participant;


import br.com.jrr.apiTest.domain.RiotGames.Match.Participant.ParticipantRepository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantsMatchService {

    @Autowired
    private ParticipantRepository participantsMatchRepository;

    // Método para buscar todos os participantes de um match (info_id)
    public List<Participant> getParticipantsByInfoId(String infoId) {
        return participantsMatchRepository.findByInfoId(infoId);
    }

    // Método para buscar um participante específico pelo ID
    public Participant getParticipantById(Long id) {
        Optional<Participant> participant = participantsMatchRepository.findById(id);
        return participant.orElseThrow(() -> new RuntimeException("Participant not found for ID: " + id));
    }
}
