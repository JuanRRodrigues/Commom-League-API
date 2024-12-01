package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.controller.TeamAndPlayerDTO;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.AccountRiotDTO;
import br.com.jrr.apiTest.domain.RiotGames.Match.DTO.SpectadorDTO;
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
import br.com.jrr.apiTest.infra.configsAPI.ApiKeyManager;
import br.com.jrr.apiTest.service.APIConfigService.ConvertData;
import br.com.jrr.apiTest.service.APIConfigService.GetData;
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


    private final GetData get = new GetData();
    private final ConvertData convert = new ConvertData();


    public static TeamDTO getCurrentTeam() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            Team team = user.getTeam();  // Obter o time do usuário

            if (team != null) {
                // Criação do LeaderDTO
                LeaderDTO leaderDTO = new LeaderDTO(
                        team.getLeader().getId(),
                        team.getLeader().getUsername(),
                        team.getLeader().getFullName(),
                        team.getLeader().getImage()
                );

                // Criação do TeamDTO
                TeamDTO teamDTO = new TeamDTO(
                        team.getId(),
                        team.getName(),
                        team.getLogo(),
                        team.getWins(),
                        team.getLoses(),
                        team.isInGame(),
                        leaderDTO,
                        TeamDTO.mapUsersToPlayerDTOs(team.getPlayers())
                );

                return teamDTO;
            }
        }

        return null; // Retorna null caso o usuário não esteja autenticado ou não tenha time
    }

    private LeaderDTO mapUserToLeaderDTO(Team team) {
        if (team != null) {
            User leader = team.getLeader();
            if (leader != null) {
                return new LeaderDTO(
                        leader.getId(),
                        leader.getFullName(),
                        leader.getLogin(),
                        leader.getImage()
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
                    User leader = team.getLeader();  // Aqui estamos pegando o líder do time
                    return new TeamDTO(
                            team.getId(),
                            team.getName(),
                            team.getLogo(),
                            team.getWins(),
                            team.getLoses(),
                            team.isInGame(),
                            leader != null ? TeamDTO.mapUserToLeaderDTO(leader) : null,  // Passa diretamente o líder
                            TeamDTO.mapUsersToPlayerDTOs(team.getPlayers())
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
                        team.getWins(),
                        team.getLoses(),
                        team.isInGame(),
                        LeaderDTO.mapUserToLeaderDTO(team.getLeader()), // Chamando o método estático
                        TeamDTO.mapUsersToPlayerDTOs(team.getPlayers()) // Mapeando os jogadores
                ))
                .orElse(null); // Retorna null se o time não for encontrado
    }




    public void addUserToTeam(String teamId, String userId) {
        // Buscar o time pelo ID
        System.out.println("3" + teamId);
        Team team = Repository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Time não encontrado"));

        System.out.println("3" + team);
        // Buscar o usuário pelo ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        System.out.println("4" + user);
        // Adicionar o usuário ao time
        team.addPlayer(user);
        user.setTeam(team);
        // Salvar o time atualizado
        Repository.save(team);
        userRepository.save(user);
    }



public UserDTO getByLogin(String login) {
        UserDetails userDetails = userRepository.findByLogin(login);
        if (userDetails != null) {
            User user = (User) userDetails;
            return new UserDTO(
                    user.getId(),
                    user.getLogin(),
                    user.getUsername(),
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



    public List<Team> saveAll(List<Team> teams) {
        return Repository.saveAll(teams);
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