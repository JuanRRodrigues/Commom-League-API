package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.domain.Team.TeamRepository;
import br.com.jrr.apiTest.controller.TeamAndPlayerDTO;
import br.com.jrr.apiTest.controller.UserDTO;
import br.com.jrr.apiTest.domain.Team.Team;
import br.com.jrr.apiTest.domain.DTO.TeamDTO;
import br.com.jrr.apiTest.domain.user.User;
import br.com.jrr.apiTest.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class TeamService {

    @Autowired
    private TeamRepository Repository;

    @Autowired
    private UserRepository userRepository;


    public List<TeamDTO> getAccount() {
        return Repository.findAll()
                .stream()
                .map(s -> new TeamDTO(s.getId(), s.getName(), s.getLogo(), s.getGame(), s.getSaldo(), s.getWins(), s.getLoses(), s.isInGame()))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUser() {
        return userRepository.findAll()
                .stream()
                .map(s -> new UserDTO(s.getId(), s.getLogin(), s.getPassword(), s.getFullName(), s.getCpf(), s.getTelefone(), s.getRole(), s.getSaldo(), s.getBirthDate(), s.getTeam(), s.getAccountRiot()))
                .collect(Collectors.toList());
    }

    public TeamDTO getById(String id) {
        Optional<Team> optionalPlayer = Repository.findById(id);
        return optionalPlayer.map(s -> new TeamDTO(s.getId(), s.getName(), s.getLogo(), s.getGame(), s.getSaldo(), s.getWins(), s.getLoses(), s.isInGame()))
                .orElse(null);
    }


    public TeamDTO addPlayer(TeamAndPlayerDTO data) {
        Optional<Team> optionalTeam = Repository.findById(data.idTime());
        Optional<User> optionalUser = userRepository.findById(data.idPlayer());

        if (optionalTeam.isPresent() && optionalUser.isPresent()) {
            Team team = optionalTeam.get();
            User user = optionalUser.get();

            user.setTeam(team);
            userRepository.save(user); // Salvando a equipe após adicionar o jogador


            return null;
        } else {
            return null; // Time ou usuário não encontrados
        }
    }
}