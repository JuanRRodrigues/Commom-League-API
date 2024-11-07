package br.com.jrr.apiTest.domain.RiotGames.Match.DTO;

import br.com.jrr.apiTest.domain.RiotGames.Match.Match;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record MatchDTO(
        String matchId,
        String gameMode,
        InfoDTO info,
        MetadadoDTO metadado  // Você pode adicionar o DTO de Metadado se necessário
) {

    // Método de conversão de Match para MatchDTO
    public static MatchDTO fromMatch(Match match) {
        if (match == null) {
            System.out.println("Match is null! Returning empty MatchDTO.");
            return new MatchDTO(null, null, null, null);  // Se match for nulo, retorna um DTO nulo
        }

        // Logs para verificar os dados do Match
        System.out.println("Converting Match to MatchDTO...");
        System.out.println("Match ID: " + match.getMatchId());
        System.out.println("Game Mode: " + match.getGameMode());

        // Convertendo o InfoDTO e MetadadoDTO (se existirem)
        InfoDTO infoDTO = InfoDTO.fromInfo(match.getInfo());  // Converte a entidade Info para o InfoDTO

        // Log para verificar os dados do InfoDTO e dos participantes
        if (infoDTO != null) {
            System.out.println("InfoDTO converted successfully!");
            if (infoDTO.getParticipants() != null && !infoDTO.getParticipants().isEmpty()) {
                System.out.println("Participants found: " + infoDTO.getParticipants().size());
                infoDTO.getParticipants().forEach(p -> System.out.println("Participant: " + p));
            } else {
                System.out.println("No participants found in InfoDTO.");
            }
        } else {
            System.out.println("InfoDTO is null!");
        }

        MetadadoDTO metadadoDTO = MetadadoDTO.fromMetadado(match.getMetadado());  // Converte Metadado se existir

        // Retorna o DTO com os dados mapeados
        return new MatchDTO(
                match.getMatchId(),
                match.getGameMode(),
                infoDTO,
                metadadoDTO
        );
    }

    // Converte uma lista de Match em uma lista de MatchDTO
    public static List<MatchDTO> fromMatchList(List<Match> matches) {
        if (matches == null || matches.isEmpty()) {
            System.out.println("No matches found. Returning empty list.");
            return Collections.emptyList();  // Retorna lista vazia se não houver matches
        }

        System.out.println("Converting list of matches to MatchDTOs...");
        return matches.stream()
                .map(MatchDTO::fromMatch)
                .collect(Collectors.toList());
    }
}
