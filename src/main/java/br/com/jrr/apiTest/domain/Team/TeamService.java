package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.controller.TeamAndPlayerDTO;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.AccountRiotDTO;
import br.com.jrr.apiTest.domain.user.DTO.UserGeneralEditDTO;
import br.com.jrr.apiTest.domain.user.DTO.LeaderDTO;
import br.com.jrr.apiTest.domain.user.DTO.UserDTO;
import br.com.jrr.apiTest.domain.user.DTO.UserLanguageEditDTO;
import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import br.com.jrr.apiTest.enums.adress.City;
import br.com.jrr.apiTest.enums.adress.Country;
import br.com.jrr.apiTest.enums.adress.State;
import br.com.jrr.apiTest.exception.AuthenticationException;
import br.com.jrr.apiTest.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
                return null;
            }
        } else {
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
                                s.getUsername(),
                                s.getPassword(),
                                s.getFullName(),
                                s.getCpf(),
                                s.getTelefone(),
                                s.getRole(),
                                s.getSaldo(),
                                s.getBirthDate(),
                                s.getImage(),
                                s.getCity(),
                                s.getCountry(),
                                s.getState(),
                            s.getLeagueRegion(),
                            s.getLanguage(),
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
                .orElse(null); //
    }




    public UserDTO getByLogin(String login) {
        UserDetails userDetails = userRepository.findByLogin(login);
        if (userDetails != null) {
            User user = (User) userDetails;
            return new UserDTO(
                    user.getId(),
                    user.getLogin(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getFullName(),
                    user.getCpf(),
                    user.getTelefone(),
                    user.getRole(),
                    user.getSaldo(),
                    user.getBirthDate(),
                    user.getImage(),
                    user.getCity(),
                    user.getCountry(),
                    user.getState(),
                    user.getLeagueRegion(),
                    user.getLanguage(),
                    AccountRiotDTO.fromAccountRiot(user.getAccountRiot())
            );
        } else {
            return null;
        }
    }

    public UserDTO getByIdUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserDTO(
                    user.getId(),
                    user.getLogin(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getFullName(),
                    user.getCpf(),
                    user.getTelefone(),
                    user.getRole(),
                    user.getSaldo(),
                    user.getBirthDate(),
                    user.getImage(),
                    user.getCity(),
                    user.getCountry(),
                    user.getState(),
                    user.getLeagueRegion(),
                    user.getLanguage(),
                    AccountRiotDTO.fromAccountRiot(user.getAccountRiot())
            );
        } else {
            return null;
        }
    }




    public UserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            return UserDTO.fromUser(user);
        }

        return null; // ou lançar uma exceção específica, se preferir
    }

    public String getAuth(UserGeneralEditDTO userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User not authenticated");
        }

        User existingUser = (User) authentication.getPrincipal();
        if (existingUser == null) {
            throw new UserNotFoundException("User not found");
        }

        if (userDto.userName() != null) {
            existingUser.setUserName(userDto.userName());
        }

        if (userDto.fullName() != null) {
            existingUser.setFullName(userDto.fullName());
        }

        // Aqui, agora vamos garantir que o valor do enum seja passado corretamente
        if (userDto.country() != null) {
            try {
                // Conversão direta para o Enum Country
                existingUser.setCountry(Country.valueOf(String.valueOf(userDto.country())));
            } catch (IllegalArgumentException e) {

            }
        }

        if (userDto.state() != null) {
            try {
                // Conversão direta para o Enum State
                existingUser.setState(State.valueOf(String.valueOf(userDto.state())));
            } catch (IllegalArgumentException e) {

            }
        }

        if (userDto.city() != null) {
            try {
                // Conversão direta para o Enum City
                existingUser.setCity(City.valueOf(String.valueOf(userDto.city())));
            } catch (IllegalArgumentException e) {

            }
        }

        userRepository.save(existingUser);

        return "User data updated successfully.";
    }


    public String editLanguage(UserLanguageEditDTO userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User not authenticated");
        }

        User existingUser = (User) authentication.getPrincipal();
        if (existingUser == null) {
            throw new UserNotFoundException("User not found");
        }

        if (userDto.language() != null) {
            existingUser.setLanguage(userDto.language());
        }
        if (userDto.leagueRegion() != null) {
            existingUser.setLeagueRegion(userDto.leagueRegion());
        }

        userRepository.save(existingUser);

        return "User data updated successfully.";

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

    public User updateUser(String id, UserGeneralEditDTO userDto) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUserName(userDto.userName());
            existingUser.setFullName(userDto.fullName());
            existingUser.setCountry(userDto.country());
            existingUser.setState(userDto.state());
            existingUser.setCity(userDto.city());
            return userRepository.save(existingUser);
        }
        return null;
    }
}