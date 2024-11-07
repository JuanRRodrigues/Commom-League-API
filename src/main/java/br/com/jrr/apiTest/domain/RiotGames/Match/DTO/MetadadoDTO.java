package br.com.jrr.apiTest.domain.RiotGames.Match.DTO;

import br.com.jrr.apiTest.domain.RiotGames.Match.Metadado.Metadado;

import java.util.List;

public record MetadadoDTO(
        String id,
        String matchId,
        String dataVersion,
        List<String> participants) {

    // Método de conversão para MetadadoDTO
    public static MetadadoDTO fromMetadado(Metadado metadadoEntity) {
        if (metadadoEntity == null) {
            return new MetadadoDTO(null, null, null, null);
        }
        return new MetadadoDTO(
                metadadoEntity.getId(),
                metadadoEntity.getMatchId(),
                metadadoEntity.getDataVersion(),
                metadadoEntity.getParticipants()
        );
    }
}
