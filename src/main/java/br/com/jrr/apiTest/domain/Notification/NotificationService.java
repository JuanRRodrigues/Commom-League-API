package br.com.jrr.apiTest.domain.Notification;

import br.com.jrr.apiTest.domain.Team.Team;
import br.com.jrr.apiTest.domain.Team.TeamRepository;
import br.com.jrr.apiTest.domain.Team.TeamService;
import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final TeamRepository teamRepository;
    private final TeamService teamService;

    public NotificationService(UserRepository userRepository,
                               NotificationRepository notificationRepository,
                               TeamRepository teamRepository, TeamService teamService) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    /**
     * Cria uma notificação e salva no banco de dados.
     *
     * @param receiverId ID do usuário que receberá a notificação
     * @param senderId   ID do usuário que enviou a notificação (opcional, pode ser null)
     * @param teamId     ID do time relacionado à notificação (opcional, pode ser null)
     * @param type       Tipo da notificação
     * @param message    Mensagem da notificação
     */
    public void createNotification(String receiverId, String senderId, String teamId,
                                   NotificationType type, String message) {
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        User sender = senderId != null ? userRepository.findById(senderId).orElse(null) : null;
        Team team = teamId != null ? teamRepository.findById(teamId).orElse(null) : null;

        System.out.println("teste" + teamId);
        Notification notification = new Notification(
                type,
                message,
                LocalDateTime.now(),
                false,
                receiver,
                sender,
                team
        );
        System.out.println(notification);
        notificationRepository.save(notification); // Salvar a notificação
    }

    /**
     * Obtém todas as notificações de um usuário.
     *
     * @param userId ID do usuário
     * @return Lista de notificações
     */
    public List<Notification> getUserNotifications(String userId) {
        System.out.println(userId);
        User user = userRepository.findById(userId)

                .orElseThrow(() -> new RuntimeException("User not found"));
        return notificationRepository.findByUser(user);
    }

    /**
     * Marca uma notificação como lida.
     *
     * @param userId         ID do usuário
     * @param notificationId ID da notificação
     */
    public void markNotificationAsRead(String userId, String notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        if (!notification.getUser().getId().equals(userId)) {
            throw new RuntimeException("Notification does not belong to the user");
        }

        notification.setRead(true);
        notificationRepository.save(notification); // Persistir a alteração
    }

    public void acceptNotification(String notificationId, String userId) {
        // Lógica para aceitar a notificação e adicionar o usuário ao time
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));

        if (notification.getType() == NotificationType.INVITE) {
            System.out.println("3");
            notification.setRead(notification.isRead());
            System.out.println("4");
            notificationRepository.save(notification);
            System.out.println("5");
            System.out.println("6");
            System.out.println(notification.getTeam());
            teamService.addUserToTeam(notification.getTeam().getId(), userId);

        }
    }

    public void sendResponseNotification(String notificationId, String userId, String status) {
        // Lógica para enviar uma notificação de resposta (aceitou ou recusou)
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));

        // Enviar notificação para quem enviou o convite
        String message = status.equals("accepted") ? "Seu convite foi aceito!" : "Seu convite foi recusado!";
        notificationRepository.save(new Notification( notification.getSender(),userId, message, status));
    }
}
