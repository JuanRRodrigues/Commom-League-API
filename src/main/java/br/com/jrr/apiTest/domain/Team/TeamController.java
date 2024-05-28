package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.controller.TeamAndPlayerDTO;
import br.com.jrr.apiTest.domain.user.User;
import br.com.jrr.apiTest.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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

    @GetMapping("/list")
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

    @PostMapping("/addPlayer")
    @Transactional
    public TeamDTO addPlayerTeam(@RequestBody @Valid TeamAndPlayerDTO data){
        return service.addPlayer(data);
    }



}
