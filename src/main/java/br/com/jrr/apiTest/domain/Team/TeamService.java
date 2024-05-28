package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.controller.TeamAndPlayerDTO;
import br.com.jrr.apiTest.domain.DTO.AccountRiotDTO;
import br.com.jrr.apiTest.domain.user.LeaderDTO;
import br.com.jrr.apiTest.domain.user.UserDTO;
import br.com.jrr.apiTest.domain.user.User;
import br.com.jrr.apiTest.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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



    private LeaderDTO mapUserToLeaderDTO(Team team) {
        if (team != null) {
            User leader = team.getLeader();
            if (leader != null) {
                return new LeaderDTO(
                        leader.getId(),
                        leader.getFullName(),
                        leader.getLogin()
                );
            } else {
                // Tratar o caso em que o líder da equipe é nulo
                return null;
            }
        } else {
            // Tratar o caso em que o time é nulo
            return null;
        }
    }

    public List<TeamDTO> getTeams() {
        return Repository.findAll()
                .stream()
                .map(team -> {
                    User leader = team.getLeader();
                    return new TeamDTO(
                            team.getId(),
                            team.getName(),
                            team.getLogo(),
                            team.getGame(),
                            team.getSaldo(),
                            team.getWins(),
                            team.getLoses(),
                            team.isInGame(),
                            mapUsersToUserDTOs(team.getPlayers()),
                            leader != null ? mapUserToLeaderDTO(leader.getTeam()) : null
                    );
                })
                .collect(Collectors.toList());
    }


    private List<UserDTO> mapUsersToUserDTOs(List<User> users) {
        return users.stream()
                .map(UserDTO::fromUser)
                .collect(Collectors.toList());
    }



    public List<UserDTO> getUser() {
        return userRepository.findAll()
                .stream()
                .map(s -> new UserDTO(
                                s.getId(),
                                s.getLogin(),
                                s.getPassword(),
                                s.getFullName(),
                                s.getCpf(),
                                s.getTelefone(),
                                s.getRole(),
                                s.getSaldo(),
                                s.getBirthDate(),
                            AccountRiotDTO.fromAccountRiot(s.getAccountRiot())
                        )
                )
                .collect(Collectors.toList());
    }



    public TeamDTO getById(String id) {
        Optional<Team> optionalTeam = Repository.findById(id);
        return optionalTeam.map(team -> new TeamDTO(
                        team.getId(),
                        team.getName(),
                        team.getLogo(),
                        team.getGame(),
                        team.getSaldo(),
                        team.getWins(),
                        team.getLoses(),
                        team.isInGame(),
                        mapUsersToUserDTOs(team.getPlayers()),
                        mapUserToLeaderDTO(team.getLeader().getTeam())
                ))
                .orElse(null); // Se não encontrar o time com o ID fornecido, retorna null
    }




    public UserDTO getByLogin(String login) {
        UserDetails userDetails = userRepository.findByLogin(login);
        if (userDetails != null) {
            User user = (User) userDetails; // Convertendo UserDetails para User
            return new UserDTO(
                    user.getId(),
                    user.getLogin(),
                    user.getPassword(),
                    user.getFullName(),
                    user.getCpf(),
                    user.getTelefone(),
                    user.getRole(),
                    user.getSaldo(),
                    user.getBirthDate(),
                    AccountRiotDTO.fromAccountRiot(user.getAccountRiot())
            );
        } else {
            return null; // ou lançar uma exceção, caso preferir
        }
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