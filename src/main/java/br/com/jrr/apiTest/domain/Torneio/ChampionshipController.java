package br.com.jrr.apiTest.domain.Torneio;

import br.com.jrr.apiTest.domain.RiotGames.Match.Match;
import br.com.jrr.apiTest.domain.RiotGames.Match.Repository.MatchLolRiotRepository;
import br.com.jrr.apiTest.domain.RiotGames.Match.TournamentMonitor;
import br.com.jrr.apiTest.domain.Team.Team;
import br.com.jrr.apiTest.domain.Team.TeamMonitor;
import br.com.jrr.apiTest.domain.Team.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/championships")
public class ChampionshipController {

    @Autowired
    private final ChampionshipService championshipService;

    @Autowired
    private final ChampionshipRepository repository;

    @Autowired
    private final MatchLolRiotRepository matchLolRepository;

    @Autowired
    private final TeamRepository teamRepository;

    @Autowired
    private final TeamMonitor teamMonitor;



    public ChampionshipController(ChampionshipService championshipService, ChampionshipRepository repository, ChampionshipRepository repository1, MatchLolRiotRepository matchLolRepository, TeamRepository teamRepository, TeamMonitor teamMonitor, TournamentMonitor tournamentMonitor) {
        this.championshipService = championshipService;
        this.repository = repository;
        this.matchLolRepository = matchLolRepository;
        this.teamRepository = teamRepository;
        this.teamMonitor = teamMonitor;

    }

    @GetMapping("/with-teams-and-matches")
    public List<ChampionshipWithTeamsAndMatchesDTO> getAllChampionshipsWithTeamsAndMatches() {
        return repository.findAllWithTeamsAndMatches();
    }

  //  @PostMapping("/populate")
 //   public List<Championship> populateChampionships() {
  //      List<Championship> championships = Arrays.asList(
  //              new Championship("Worlds 2024", "Tier 1", true, null, null, 500000.00, "Finals", "International", "Team A"),
   //             new Championship("Spring Split 2024", "Tier 2", true, null, null, 200000.00, "Playoffs", "Regional", "Team B"),
   //             new Championship("Summer Split 2024", "Tier 2", true, null, null, 300000.00, "Semifinals", "Regional", "Team C"),
    //            new Championship("MSI 2024", "Tier 1", true, null, null, 400000.00, "Finals", "International", "Team D"),
    //            new Championship("All-Stars 2024", "Exhibition", false, null, null, 100000.00, "Group Stage", "Exhibition", "N/A")
   //     );



   //     return championshipService.saveAll(championships);
   // }

    @PutMapping("/update/{id}")
    public ResponseEntity<Championship> updateChampionship(
            @PathVariable String id,
            @RequestBody ChampionshipUpdateRequest updatedChampionshipRequest) {

        // Logando o início da atualização
        System.out.println("Iniciando a atualização do campeonato com id: " + id);

        // Buscar o campeonato pelo id
        Championship existingChampionship = repository.findById(id).orElse(null);

        if (existingChampionship == null) {
            System.out.println("Campeonato com id: " + id + " não encontrado");
            return ResponseEntity.notFound().build();
        }

        // Logando os dados recebidos na requisição
        System.out.println("Dados recebidos na requisição: " + updatedChampionshipRequest);

        // Atualizando os dados do campeonato
        existingChampionship.setNome(updatedChampionshipRequest.getNome());
        existingChampionship.setRanking(updatedChampionshipRequest.getRanking());
        existingChampionship.setInTorneio(updatedChampionshipRequest.isInTorneio());
        existingChampionship.setPrize(updatedChampionshipRequest.getPrize());
        existingChampionship.setImg(updatedChampionshipRequest.getImg());
        existingChampionship.setChampionship(updatedChampionshipRequest.getChampionship());
        existingChampionship.setWinner(updatedChampionshipRequest.getWinner());

        // Atualizando os times
        if (updatedChampionshipRequest.getTeam() != null && !updatedChampionshipRequest.getTeam().isEmpty()) {
            List<Team> teams = new ArrayList<>();
            for (Team team : updatedChampionshipRequest.getTeam()) {
                System.out.println("Verificando se o time com id: " + team.getId() + " existe no banco de dados");
                Team existingTeam = teamRepository.findById(team.getId()).orElse(null);
                if (existingTeam != null) {
                    teams.add(existingTeam);
                    System.out.println("Time com id: " + team.getId() + " encontrado e adicionado");
                } else {
                    System.out.println("Time com id: " + team.getId() + " não encontrado");
                    return ResponseEntity.badRequest().body(null);
                }
            }
            existingChampionship.setTimes(teams);
            System.out.println("Times atualizados com sucesso para o campeonato com id: " + id);
        }

        // Atualizando as partidas
        if (updatedChampionshipRequest.getPartidas() != null && !updatedChampionshipRequest.getPartidas().isEmpty()) {
            List<Match> updatedMatches = new ArrayList<>();
            for (Match match : updatedChampionshipRequest.getPartidas()) {
                System.out.println("Verificando se a partida com id: " + match.getId() + " existe no banco de dados");
                Match existingMatch = matchLolRepository.findById(match.getId()).orElse(null);
                if (existingMatch != null) {
                    // Atualizando as partidas individualmente
                    existingMatch.setTournament(existingChampionship);  // Associando a partida com o campeonato
                    matchLolRepository.save(existingMatch);  // Salvando a partida atualizada
                    updatedMatches.add(existingMatch);
                    System.out.println("Partida com id: " + match.getId() + " encontrada e salva");
                } else {
                    System.out.println("Partida com id: " + match.getId() + " não encontrada");
                    return ResponseEntity.badRequest().body(null);
                }
            }
            existingChampionship.setPartidas(updatedMatches);  // Associando as partidas atualizadas ao campeonato
            System.out.println("Partidas atualizadas com sucesso para o campeonato com id: " + id);
        }

        // Salvando o campeonato atualizado
        System.out.println("Salvando campeonato atualizado com id: " + id);
        Championship savedChampionship = repository.save(existingChampionship);
        System.out.println("Campeonato atualizado com sucesso: " + savedChampionship);

        return ResponseEntity.ok(savedChampionship);
    }

    @PostMapping("/generate")
    public ResponseEntity<Championship> generateChampionship() {
        // Logando o início da geração
        System.out.println("Gerando um novo campeonato...");

        // Buscar times disponíveis para jogar
    //    List<Team> readyTeams = teamRepository.findAll()
    //            .stream()
     //           .filter(Team::isReadyToPlay) // Filtra os times prontos para jogar
    //
        //            .collect(Collectors.toList());


        // Selecionar times aleatórios
        // Buscar todos os times disponíveis
        List<Team> readyTeams = teamRepository.findAll();

        if (readyTeams.size() < 2) {
            System.out.println("Não há times suficientes disponíveis para criar um campeonato.");
            return ResponseEntity.badRequest().body(null);
        }

        Collections.shuffle(readyTeams);
        List<Team> selectedTeams = readyTeams.subList(0, Math.min(readyTeams.size(), 8)); // Máximo de 8 times

        if (readyTeams.size() < 2) {
            System.out.println("Não há times suficientes disponíveis para criar um campeonato.");
            return ResponseEntity.badRequest().body(null);
        }



        // Criar um novo campeonato
        Championship newChampionship = new Championship();
        newChampionship.setNome("Campeonato Gerado " + UUID.randomUUID());
        newChampionship.setRanking("Tier 3");
        newChampionship.setInTorneio(true);
        newChampionship.setImg("7");
        newChampionship.setPrize(100000.00);
        newChampionship.setChampionship("Regional");
        newChampionship.setTimes(selectedTeams);


        // Gerar os rounds e partidas
        championshipService.generateRounds(newChampionship);

        System.out.println(newChampionship);
        // Salvar o campeonato
        Championship savedChampionship = repository.save(newChampionship);

        System.out.println("chegou " + savedChampionship);
        // Iniciar monitoramento para os times selecionados
      //  selectedTeams.forEach(team -> teamMonitor.addTeamToMonitor(team.getId(), match.getId()));

        // Logando o sucesso
        System.out.println("Novo campeonato criado com sucesso: " + savedChampionship);

        return ResponseEntity.ok(savedChampionship);
    }


    @Transactional
    @GetMapping("")
    public ResponseEntity<List<Championship>> getAllChampionships() {
        // Buscar todos os campeonatos
        List<Championship> championships = repository.findAll();

        // Buscar campeonato com o ID específico
        String specificId = "c9f27dbb-d202-436a-bb90-2929d36e7db8";
        Optional<Championship> foundChampionship = repository.findById(specificId);

        if (foundChampionship.isPresent()) {
            System.out.println("Campeonato com ID específico encontrado: " + foundChampionship.get());
        } else {
            System.out.println("Campeonato com ID específico não encontrado");
        }

        // Iterar sobre os campeonatos encontrados
        for (Championship championship : championships) {
            System.out.println("Buscando campeonato com ID: " + championship.getId());

            // Buscar o campeonato por ID
            Optional<Championship> foundChampionshipById = repository.findById(championship.getId());

            // Verificar o que foi encontrado e imprimir
            if (foundChampionshipById.isPresent()) {
                System.out.println("Campeonato encontrado: " + foundChampionshipById.get());
            } else {
                System.out.println("Campeonato com ID: " + championship.getId() + " não encontrado");
            }
        }

        // Verificar se a lista de campeonatos está vazia
        if (championships.isEmpty()) {
            System.out.println("Nenhum campeonato encontrado.");
            return ResponseEntity.noContent().build(); // Retorna 204 caso não haja campeonatos
        }

        Optional<Championship> teste = repository.findById("c9f27dbb-d202-436a-bb90-2929d36e7db8");
        System.out.println(teste);
        // Retornar a lista de campeonatos
        return ResponseEntity.ok(championships); // Retorna 200 com a lista de campeonatos
    }

    @GetMapping("/{id}")
    public ResponseEntity<Championship> getChampionshipById(@PathVariable String id) {

        // Logando o início da busca
        System.out.println("Buscando o campeonato com id: " + id);

        // Buscar o campeonato pelo id, incluindo as entidades relacionadas (times e partidas)
        Optional<Championship> optionalChampionship = repository.findById(id);

        if (optionalChampionship.isEmpty()) {
            // Caso o campeonato não seja encontrado
            System.out.println("Campeonato com id: " + id + " não encontrado");
            return ResponseEntity.notFound().build();
        }

        // Caso o campeonato seja encontrado, retornamos ele
        Championship championship = optionalChampionship.get();
        System.out.println("Campeonato encontrado: " + championship);

        // Garantir que os times e partidas sejam carregados corretamente (dependendo da configuração de fetch)
        // Isso só é necessário se os dados não estiverem sendo carregados automaticamente com EAGER
        if (championship.getTimes().isEmpty()) {
            System.out.println("Nenhum time associado ao campeonato com id: " + id);
        } else {
            System.out.println("Times associados ao campeonato: " + championship.getTimes());
        }

        if (championship.getPartidas().isEmpty()) {
            System.out.println("Nenhuma partida associada ao campeonato com id: " + id);
        } else {
            System.out.println("Partidas associadas ao campeonato: " + championship.getPartidas());
        }

        return ResponseEntity.ok(championship); // Retorna o campeonato encontrado com seus dados associados
    }

}
