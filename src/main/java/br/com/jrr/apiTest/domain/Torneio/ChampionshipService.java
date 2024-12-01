package br.com.jrr.apiTest.domain.Torneio;


import br.com.jrr.apiTest.domain.RiotGames.Match.Match;
import br.com.jrr.apiTest.domain.RiotGames.Match.Repository.MatchLolRiotRepository;
import br.com.jrr.apiTest.domain.Team.Team;
import br.com.jrr.apiTest.domain.Team.TeamMonitor;
import br.com.jrr.apiTest.domain.Team.TeamRepository;
import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChampionshipService {

    private final ChampionshipRepository championshipRepository;

    private final TeamRepository teamRepository;

    private final UserRepository userRepository;

    private final TeamMonitor teamMonitor;

    private final MatchLolRiotRepository matchLolRiotRepository;

    public ChampionshipService(ChampionshipRepository championshipRepository, TeamRepository teamRepository, UserRepository userRepository, TeamMonitor teamMonitor, MatchLolRiotRepository matchLolRiotRepository) {
        this.championshipRepository = championshipRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.teamMonitor = teamMonitor;
        this.matchLolRiotRepository = matchLolRiotRepository;
    }


    void generateRounds(Championship championship) {
        // Lógica para gerar os rounds
        List<Team> teams = championship.getTimes();
        List<Match> matches = new ArrayList<>();

        // Garantir que o número de times é par, se não, adicionar um "bye"
        if (teams.size() % 2 != 0) {
            Team teamWithBye = teams.remove(teams.size() - 1); // Remove o último time para dar o "bye"
            System.out.println("O time " + teamWithBye.getName() + " recebeu um bye.");
        }

        // Gerar confrontos para o primeiro round
        while (teams.size() > 1) {
            Team team1 = teams.remove(0);
            Team team2 = teams.remove(0);
            System.out.println("Confronto: " + team1.getName() + " vs " + team2.getName());
            Match match = new Match(team1, team2);
            matches.add(match);
        }

        System.out.println(matches);
        // Adicionar as partidas ao campeonato
        championship.setPartidas(matches);
        championship.setRound("Fase de Grupos");  // Fase inicial (pode ser ajustada conforme necessário)

        matches.forEach(matchLolRiotRepository::save);

        // Iniciar o monitoramento das partidas com o TeamMonitor
        matches.forEach(match -> {
            // Chamar o monitor para acompanhar as partidas
            teamMonitor.addTeamToMonitor(match.getTeam1().getId(), match.getId());
            teamMonitor.addTeamToMonitor(match.getTeam2().getId(), match.getId());

            System.out.println("11" + match.getId());
        });


        // Salva cada partida individualmente



        // Agora, o monitoramento será feito pelo TeamMonitor, que irá
        // acompanhar as partidas e atualizar os vencedores conforme as partidas
        // forem finalizadas e os resultados forem obtidos.

        // Lógica adicional caso você precise atualizar a competição
        // quando todos os resultados estiverem disponíveis
        championship.setRound("Fase de Grupos");  // Fase inicial (pode ser ajustada conforme necessário)

        // Não há necessidade de simulação de vencedor; a lógica de vencedores
        // será gerenciada pelo TeamMonitor que irá verificar as partidas em andamento.
    }

    private Team simulateMatch(Match match) {
        // Lógica para simular o vencedor da partida
        // Aqui você pode implementar uma lógica de simulação ou esperar o resultado real das partidas
        // Por enquanto, vamos supor que o vencedor é o time1
        System.out.println("Partida entre " + match.getTeam1().getName() + " e " + match.getTeam2().getName());
        return match.getTeam1();  // Simulando que o time1 sempre vence
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
