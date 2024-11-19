package br.com.jrr.apiTest.domain.Torneio;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionshipService {

    private final ChampionshipRepository championshipRepository;

    public ChampionshipService(ChampionshipRepository championshipRepository) {
        this.championshipRepository = championshipRepository;
    }

    public List<Championship> getAllChampionships() {
        return championshipRepository.findAll();
    }

    public Championship getChampionshipById(String id) {
        return championshipRepository.findById(id).orElseThrow(() -> new RuntimeException("Championship not found"));
    }

    public Championship createChampionship(Championship championship) {
        return championshipRepository.save(championship);
    }

    public Championship updateChampionship(String id, Championship championshipDetails) {
        Championship championship = getChampionshipById(id);
        championship.setNome(championshipDetails.getNome());
      //  championship.set(championshipDetails.getName());
       // championship.setRegion(championshipDetails.getRegion());
      //  championship.setYear(championshipDetails.getYear());
        return championshipRepository.save(championship);
    }

    public List<Championship> saveAll(List<Championship> championships) {
        return championshipRepository.saveAll(championships);
    }

    public void deleteChampionship(String id) {
        championshipRepository.deleteById(id);
    }
}
