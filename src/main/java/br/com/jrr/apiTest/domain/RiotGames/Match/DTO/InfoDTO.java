package br.com.jrr.apiTest.domain.RiotGames.Match.DTO;

import br.com.jrr.apiTest.domain.RiotGames.Match.DTO.ParticipantDTO;
import br.com.jrr.apiTest.domain.RiotGames.Match.Info.Info;

import java.util.List;
import java.util.stream.Collectors;

public class InfoDTO {

    private String id;

    private String gameId;

    private String gameMode;

    private String gameName;

    private String gameType;

    private String gameDuration;

    private String gameVersion;

    private String endOfGameResult;

    private String gameCreation;

    private String gameEndTimestamp;

    private List<ParticipantDTO> participants;

    public static InfoDTO fromInfo(Info info) {
        if (info == null) {
            return null;  // Retorna null se a Info for nula
        }

        InfoDTO infoDTO = new InfoDTO();

        // Adiciona o id da Info
        infoDTO.setId(info.getId()); // Aqui você set o ID da Info na DTO

        infoDTO.setGameId(info.getGameId());
        infoDTO.setGameMode(info.getGameMode());
        infoDTO.setGameName(info.getGameName());
        infoDTO.setGameType(info.getGameType());
        infoDTO.setGameDuration(info.getGameDuration());
        infoDTO.setGameVersion(info.getGameVersion());
        infoDTO.setEndOfGameResult(info.getEndOfGameResult());
        infoDTO.setGameCreation(info.getGameCreation());
        infoDTO.setGameEndTimestamp(info.getGameEndTimestamp());

        if (info.getParticipants() != null) {
            List<ParticipantDTO> participantsDTO = info.getParticipants().stream()
                    .map(participant -> {
                        System.out.println("Convertendo participante: " + participant); // Log de depuração
                        return new ParticipantDTO(
                                participant.getId(),
                                participant.getWin(),
                                participant.getChampionId(),
                                participant.getBaronKills(),
                                participant.getRiotIdGameName(),
                                participant.getQuadraKills(),
                                participant.getPuuid(),
                                participant.getIndividualPosition(),
                                participant.getGameEndedInSurrender(),
                                participant.getDeaths(),
                                participant.getDoubleKills(),
                                participant.getDragonKills(),
                                participant.getChampLevel(),
                                participant.getRiotIdTagline(),
                                participant.getTripleKills()
                        );
                    })
                    .collect(Collectors.toList());
            infoDTO.setParticipants(participantsDTO);
        } else {
            System.out.println("Nenhum participante encontrado em Info.");
        }

        return infoDTO;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getGameId() { return gameId; }
    public void setGameId(String gameId) { this.gameId = gameId; }

    public String getGameMode() { return gameMode; }
    public void setGameMode(String gameMode) { this.gameMode = gameMode; }

    public String getGameName() { return gameName; }
    public void setGameName(String gameName) { this.gameName = gameName; }

    public String getGameType() { return gameType; }
    public void setGameType(String gameType) { this.gameType = gameType; }

    public String getGameDuration() { return gameDuration; }
    public void setGameDuration(String gameDuration) { this.gameDuration = gameDuration; }

    public String getGameVersion() { return gameVersion; }
    public void setGameVersion(String gameVersion) { this.gameVersion = gameVersion; }

    public String getEndOfGameResult() { return endOfGameResult; }
    public void setEndOfGameResult(String endOfGameResult) { this.endOfGameResult = endOfGameResult; }

    public String getGameCreation() { return gameCreation; }
    public void setGameCreation(String gameCreation) { this.gameCreation = gameCreation; }

    public String getGameEndTimestamp() { return gameEndTimestamp; }
    public void setGameEndTimestamp(String gameEndTimestamp) { this.gameEndTimestamp = gameEndTimestamp; }

    public List<ParticipantDTO> getParticipants() { return participants; }
    public void setParticipants(List<ParticipantDTO> participants) { this.participants = participants; }
}
