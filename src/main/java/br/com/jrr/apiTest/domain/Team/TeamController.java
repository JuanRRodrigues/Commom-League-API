package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.controller.TeamAndPlayerDTO;
import br.com.jrr.apiTest.domain.DTO.TeamDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teams")
public class TeamController {

@Autowired
private TeamService service;

    @Autowired
    private TeamRepository Repository;

    @GetMapping("/list")
    public List<TeamDTO> getAccount() {
        return service.getAccount();
    }

    @GetMapping("/{id}")
    public TeamDTO getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid TeamDTO data){

        Team newTeam = new Team(data.name(), data.logo(),  data.game(), data.saldo(),data.wins(), data.loses(), data.inGame());

        System.out.println(newTeam);
        this.Repository.save(newTeam);

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
