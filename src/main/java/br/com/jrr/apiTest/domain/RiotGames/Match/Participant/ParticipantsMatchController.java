package br.com.jrr.apiTest.domain.RiotGames.Match.Participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
public class ParticipantsMatchController {

    @Autowired
    private ParticipantsMatchService participantsMatchService;

    // Endpoint para obter todos os participantes de um match (por info_id)
    @GetMapping("/match/{infoId}")
    public List<Participant> getParticipantsByMatchId(@PathVariable String infoId) {
        return participantsMatchService.getParticipantsByInfoId(infoId);
    }

    // Endpoint para buscar um participante específico por ID
    @GetMapping("/{id}")
    public Participant getParticipantById(@PathVariable Long id) {
        return participantsMatchService.getParticipantById(id);
    }
}
