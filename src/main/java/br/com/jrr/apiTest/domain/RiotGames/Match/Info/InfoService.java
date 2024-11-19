package br.com.jrr.apiTest.domain.RiotGames.Match.Info;


import br.com.jrr.apiTest.domain.RiotGames.Match.Info.infoRepository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;

    // Método para criar ou salvar uma nova entidade Info
    public Info saveInfo(Info info) {
        return infoRepository.save(info);
    }

    // Método para buscar todas as entidades Info
    public List<Info> getAllInfos() {
        return infoRepository.findAll();
    }

    // Método para buscar uma entidade Info por ID
    public Optional<Info> getInfoById(String id) {
        return infoRepository.findById(id);
    }

    // Método para atualizar uma entidade Info existente
    public Info updateInfo(String id, Info updatedInfo) {
        return infoRepository.findById(id).map(info -> {
            info.setGameId(updatedInfo.getGameId());
            info.setGameMode(updatedInfo.getGameMode());
            info.setGameName(updatedInfo.getGameName());
            info.setGameType(updatedInfo.getGameType());
            info.setGameDuration(updatedInfo.getGameDuration());
            info.setGameVersion(updatedInfo.getGameVersion());
            info.setEndOfGameResult(updatedInfo.getEndOfGameResult());
            info.setGameCreation(updatedInfo.getGameCreation());
            info.setGameEndTimestamp(updatedInfo.getGameEndTimestamp());
            info.setParticipants(updatedInfo.getParticipants());
            return infoRepository.save(info);
        }).orElseThrow(() -> new RuntimeException("Info not found with id: " + id));
    }

    // Método para deletar uma entidade Info por ID
    public void deleteInfo(String id) {
        if (infoRepository.existsById(id)) {
            infoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Info not found with id: " + id);
        }
    }

    // Método para buscar informações por MatchID
    public Optional<Info> getInfoByMatchId(String gameId) {
        // Realiza a consulta no banco de dados pelo MatchID fornecido

        // Se o MatchID for encontrado no banco de dados, retorna a informação correspondente
        // Se não encontrar nenhum dado para o MatchID, retorna um Optional vazio
        // Caso o matchId não exista no banco de dados
        return infoRepository.findByGameId(gameId);
    }

    public Page<Info> getInfosByPuuid(String puuid, Pageable pageable) {
        return infoRepository.findByParticipantPuuid(puuid, pageable);
    }
}
