package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.controller.TeamAndPlayerDTO;
import br.com.jrr.apiTest.domain.Notification.Notification;
import br.com.jrr.apiTest.domain.Notification.NotificationRepository;
import br.com.jrr.apiTest.domain.Notification.NotificationType;
import br.com.jrr.apiTest.domain.file.model.FileService;
import br.com.jrr.apiTest.domain.file.model.FileUploadRequest;
import br.com.jrr.apiTest.domain.file.model.FileUploadResponse;
import br.com.jrr.apiTest.domain.user.DTO.UserDTO;
import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
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
    private NotificationRepository notificationRepository;
    @Autowired
    private FileService fileService;

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


    @PostMapping("/invite/{teamId}")
    @Transactional
    public ResponseEntity<?> invitePlayerToTeam(@PathVariable String teamId, @Valid @RequestBody  TeamRegisterDTO data) {
        // Recupera o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
        }

        System.out.println(data);
        User currentUser = (User) authentication.getPrincipal();

        // Buscar o time pelo ID
        Optional<Team> optionalTeam = Repository.findById(teamId);
        if (optionalTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Time não encontrado.");
        }

        Team team = optionalTeam.get();

        // Verificar se o usuário autenticado é o líder do time
        if (!team.getLeader().equals(currentUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas o líder do time pode enviar convites.");
        }

        // Processa a lista de jogadores (usernames)
        for (String userName : data.players()) {  // Supondo que 'players' seja um ArrayList de usernames
            Optional<User> playerOpt = Optional.ofNullable(userRepository.findByUserName(userName));  // Buscar o usuário pelo username

            if (playerOpt.isPresent()) {
                User player = playerOpt.get();

                // Verifica se o jogador já está em um time
                if (player.getTeam() != null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Jogador " + userName + " já faz parte de um time.");
                }

                // Adiciona o jogador ao time
             //   team.addPlayer(player);
              //  player.setTeam(team);
              //  userRepository.save(player);

                // Criar a notificação para o jogador
                String message = "Você foi convidado para se juntar ao time: " + team.getName();
                Notification invitation = new Notification(
                                        NotificationType.INVITE,
                                        message,
                                        LocalDateTime.now(),
                        false,
                                        team,
                                        player,
                        currentUser
                                );
                invitation.setUser(player);
                invitation.setTeam(team);
                invitation.setSender(currentUser);

                System.out.println(invitation + "teste");
                // Salvar a notificação
                notificationRepository.save(invitation);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador " + userName + " não encontrado.");
            }
        }

        // Salvar o time com os novos jogadores
       // Repository.save(team);

        // Resposta de sucesso
        return ResponseEntity.ok("Convites enviados para os jogadores.");
    }


    @Transactional
    @PostMapping("/register")
    public ResponseEntity register(@Valid TeamRegisterDTO data, @RequestParam("file") MultipartFile file) throws IOException {

        // Obter a autenticação do usuário atual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build(); // Retornar 401 caso o usuário não esteja autenticado
        }

        // Obter o usuário a partir do contexto de segurança
        User user = (User) authentication.getPrincipal(); // Obtém o usuário autenticado

        // Caso o usuário não tenha sido encontrado no contexto de segurança
        if (user == null) {
            return ResponseEntity.notFound().build(); // Retornar 404 caso o usuário não seja encontrado
        }

        // Criação do arquivo de imagem do time (por exemplo, logo)
        FileUploadRequest fileUploadRequest = new FileUploadRequest(null, file, data.name());  // Corrigido o parâmetro para usar o nome do time
        FileUploadResponse fileUploadResponse = fileService.upload(fileUploadRequest);  // Chama o serviço para salvar o arquivo

        // Criar um novo time
        Team newTeam = new Team(
                data.name(),
                fileUploadResponse.id(),
                null,
                0.00,
                0,
                0,
                false,
                null,
                new ArrayList<>());



        System.out.println("Leader" + user);


        newTeam.addLeader(user);
        user.setTeam(newTeam);

        Team savedTeam = Repository.save(newTeam);
        userRepository.save(user);
        System.out.println(newTeam);
        // Adicionar o jogador e o líder (usando o usuário encontrado no contexto)
       // newTeam.addPlayer(user);


        System.out.println("2" + newTeam);

        // Buscar os jogadores pelo username e adicionar ao time
        for (String userName : data.players()) {
            User player = userRepository.findByUserName(userName);  // Buscar o usuário pelo userName
            System.out.println(player);
            if (player != null) {
                System.out.println("Salvando" + player.getLogin());
                savedTeam.addPlayer(player);  // Adiciona o jogador ao time
                player.setTeam(savedTeam);
                userRepository.save(player);
            }else{
                System.out.println("deu ruim");
            }
        }
        System.out.println(newTeam + "salvanod....");
        // Salvar o time

        Repository.save(savedTeam);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/current-team")
    public ResponseEntity<TeamDTO> getCurrentTeam() {
        TeamDTO team = TeamService.getCurrentTeam();

        if (team != null) {
            return ResponseEntity.ok(team);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/leave-team/{teamId}")
    @Transactional
    public ResponseEntity<?> leaveTeam(@PathVariable String teamId) {
        // Obter a autenticação do usuário atual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Obter o usuário a partir do contexto de segurança
        User user = (User) authentication.getPrincipal();

        // Buscar o time pelo ID
        Optional<Team> optionalTeam = Repository.findById(teamId);
        if (optionalTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Time não encontrado.");
        }

        Team team = optionalTeam.get();

        // Verificar se o usuário é membro do time
        if (!team.getPlayers().contains(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Você não é um membro deste time.");
        }

        // Verificar se o usuário é o líder do time
        if (user.equals(team.getLeader())) {
            if (team.getPlayers().size() > 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("O líder não pode sair enquanto houver outros membros no time.");
            }

            // Se o líder é o único membro, desfazer o time
            Repository.delete(team);
            return ResponseEntity.ok("Você saiu do time, e o time foi desfeito.");
        }

        // Remover o usuário da lista de jogadores do time
        team.getPlayers().remove(user);

        // Atualizar o time no banco de dados
        Repository.save(team);

        // Remover o time associado ao usuário
        user.setTeam(null);
        userRepository.save(user);

        return ResponseEntity.ok("Você saiu do time com sucesso.");
    }


    @PutMapping("/transfer-leadership/{teamId}")
    @Transactional
    public ResponseEntity<?> transferLeadership(@PathVariable String teamId, @RequestBody @Valid Team data) {
        // Obter a autenticação do usuário atual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Obter o usuário autenticado
        User currentUser = (User) authentication.getPrincipal();

        // Buscar o time pelo ID
        Optional<Team> optionalTeam = Repository.findById(data.getId());
        if (optionalTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Time não encontrado.");
        }

        Team team = optionalTeam.get();

        // Verificar se o usuário atual é o líder do time
        if (!team.getLeader().equals(currentUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas o líder do time pode transferir a liderança.");
        }

        // Buscar o novo líder pelo ID
        Optional<User> optionalNewLeader = userRepository.findById(data.getLeader().getId());
        if (optionalNewLeader.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Novo líder não encontrado.");
        }

        User newLeader = optionalNewLeader.get();

        // Verificar se o novo líder é membro do time
        if (!team.getPlayers().contains(newLeader)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O novo líder deve ser um membro do time.");
        }

        // Criar a notificação para o novo líder
        String newLeaderMessage = "Você foi nomeado o novo líder do time " + team.getName();
        Notification newLeaderNotification = new Notification(
                NotificationType.LEADERSHIP_TRANSFER,
                newLeaderMessage,
                LocalDateTime.now(),
                false,
                team,
                null,
                currentUser


        );
        newLeaderNotification.setUser(newLeader);
        notificationRepository.save(newLeaderNotification);

        // Criar a notificação para o líder atual que perdeu a liderança
        String currentLeaderMessage = "Você não é mais o líder do time " + team.getName();
        Notification currentLeaderNotification = new Notification(
                NotificationType.LEADERSHIP_TRANSFER,
                currentLeaderMessage,
                LocalDateTime.now(),
                false,
                team,
                null,
                currentUser
        );
        currentLeaderNotification.setUser(currentUser);
        notificationRepository.save(currentLeaderNotification);

        // Criar as notificações para todos os membros do time
        for (User player : team.getPlayers()) {
            if (!player.equals(currentUser)) {  // Não notificar o líder atual que perdeu a liderança
                String playerMessage = "O novo líder do time " + team.getName() + " é " + newLeader.getUsername();
                Notification playerNotification = new Notification(
                        NotificationType.LEADERSHIP_TRANSFER,
                        playerMessage,
                        LocalDateTime.now(),
                        false,
                        team,
                        null,
                        currentUser
                );
                playerNotification.setUser(player);
                notificationRepository.save(playerNotification);
            }
        }
        // Transferir a liderança
        team.setLeader(newLeader);
        // Salvar as alterações no banco de dados
        Repository.save(team);

        return ResponseEntity.ok("Liderança transferida com sucesso para " + newLeader.getUsername() + ".");
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

    @PutMapping("/update/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable String id, @RequestBody @Valid Team data) {
        // Buscar o time pelo ID
        Optional<Team> optionalTeam = Repository.findById(id);
        if (optionalTeam.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retornar 404 se o time não for encontrado
        }

        // Obter o time existente
        Team team = optionalTeam.get();

        // Atualizar os dados do time
        team.setName(data.getName()); // Atualiza o nome
        team.setLogo(data.getLogo()); // Atualiza o logo
        team.setGame(data.getGame()); // Atualiza o jogo
        team.setInGame(data.isInGame()); // Atualiza o status de estar em jogo

        // Salvar as atualizações no banco de dados
        Team updatedTeam = Repository.save(team);

        // Retornar a resposta com o time atualizado
        return ResponseEntity.ok(updatedTeam);
    }

    @PostMapping("/addPlayer")
    @Transactional
    public TeamDTO addPlayerTeam(@RequestBody @Valid TeamAndPlayerDTO data){
        return service.addPlayer(data);
    }


    @DeleteMapping("/ban/{userId}")
    @Transactional
    public ResponseEntity<?> banPlayerFromTeam(@PathVariable String userId) {
        // Obter a autenticação do usuário atual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Obter o usuário a partir do contexto de segurança
        User currentUser = (User) authentication.getPrincipal();

        // Buscar o time pelo ID
        Optional<Team> optionalTeam = Repository.findById(currentUser.getTeam().getId());
        if (optionalTeam.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Time não encontrado.");
        }

        Team team = optionalTeam.get();

        // Verificar se o usuário é o líder do time
        if (!team .getLeader().equals(currentUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas o líder do time pode banir jogadores.");
        }

        // Buscar o jogador a ser banido
        Optional<User> optionalPlayer = userRepository.findById(userId);
        if (optionalPlayer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não encontrado.");
        }

        User playerToBan = optionalPlayer.get();

        // Verificar se o jogador está no time
        if (!team.getPlayers().contains(playerToBan)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este jogador não faz parte do time.");
        }

        // Remover o jogador da lista de jogadores do time
        team.getPlayers().remove(playerToBan);

        // Atualizar o time no banco de dados
        Repository.save(team);

        // Remover o time associado ao jogador
        playerToBan.setTeam(null);
        userRepository.save(playerToBan);

        // Criar notificação de banimento
        String message = "Você foi banido do time: " + team.getName();
        Notification banNotification = new Notification(
                NotificationType.BAN,
                message,
                LocalDateTime.now(),
                false,
                team,
                null,
                currentUser
        );
        banNotification.setUser(playerToBan);
        notificationRepository.save(banNotification);

        // Retornar resposta de sucesso
        return ResponseEntity.ok("Jogador banido do time com sucesso.");
    }


}
