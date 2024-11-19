package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.controller.TeamAndPlayerDTO;
import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/teams")
public class TeamController {

@Autowired
private TeamService service;

    @Autowired
    private TeamRepository Repository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public List<TeamDTO> getAccount() {
        return service.getTeams();
    }

    @GetMapping("/{id}")
    public TeamDTO getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid TeamRegistreDTO data){

        // Encontrar o usuário pelo ID
        Optional<User> optionalUser = userRepository.findById(data.idUser());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retornar 404 se o usuário não for encontrado
        }

        Team newTeam = new Team(data.name(), null,  data.game(),00.00,0, 0, false, null, new ArrayList<>());

        newTeam.addPlayer(optionalUser.get());
        newTeam.addLeader(optionalUser.get());

        Team savedTeam = Repository.save(newTeam);

        User user = optionalUser.get();
        user.setTeam(newTeam);
        userRepository.save(user);



        this.Repository.save(newTeam);


        return ResponseEntity.ok().build();
    }


    @PostMapping("/join")
    public ResponseEntity joinTeam(@RequestBody @Valid TeamJoinDTO data){

        // Encontrar o usuário pelo ID
        Optional<User> optionalUser = userRepository.findById(data.idUser());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retornar 404 se o usuário não for encontrado
        }

        // Encontrar o time pelo ID
        Optional<Team> optionalTeam = Repository.findById(data.idTeam());
        if (optionalTeam.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retornar 404 se o time não for encontrado
        }

        Team team = optionalTeam.get();
        team.addPlayer(optionalUser.get());

        Team savedTeam = Repository.save(team);

        User user = optionalUser.get();
        user.setTeam(savedTeam);
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public TeamDTO delete(@PathVariable String id){
        Repository.deleteById(id);
        return service.getById(id);
    }

    @PostMapping("/populate")
    public List<Team> populateTeams() {
        List<Team> teams = Arrays.asList(
                new Team("Red Warriors", "red-logo.png", "League of Legends", 5000.00, 15, 5, true, null, null),
                new Team("Blue Sharks", "blue-logo.png", "Valorant", 3000.00, 10, 10, true, null, null),
                new Team("Green Titans", "green-logo.png", "CS:GO", 4000.00, 20, 2, true, null, null),
                new Team("Yellow Phoenix", "yellow-logo.png", "Dota 2", 2500.00, 8, 12, false, null, null),
                new Team("Black Panthers", "black-logo.png", "League of Legends", 6000.00, 25, 0, true, null, null)
        );

        return service.saveAll(teams);
    }

    @PostMapping("/addPlayer")
    @Transactional
    public TeamDTO addPlayerTeam(@RequestBody @Valid TeamAndPlayerDTO data){
        return service.addPlayer(data);
    }



}
