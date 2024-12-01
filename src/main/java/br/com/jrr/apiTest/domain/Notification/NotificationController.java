package br.com.jrr.apiTest.domain.Notification;

import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/{userId}/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationService notificationService, UserRepository userRepository, NotificationRepository notificationRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    /**
     * Cria uma nova notificação para o usuário especificado.
     *
     * @param userId          ID do usuário que receberá a notificação
     * @param notificationDto Dados da notificação enviados pelo cliente
     * @return ResponseEntity indicando o status de criação
     */
    @PostMapping
    public ResponseEntity<Void> createNotification(
            @PathVariable String userId,
            @RequestBody NotificationDto notificationDto) {

        System.out.println(notificationDto + "teste");
        notificationService.createNotification(
                userId,
                notificationDto.getSenderId(),  // ID do remetente (opcional)
                notificationDto.getTeamId(),    // ID do time (opcional)
                notificationDto.getType(),
                notificationDto.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Retorna todas as notificações do usuário especificado.
     *
     * @param userId ID do usuário
     * @return Lista de notificações do usuário
     */
    @GetMapping
    public List<Notification> getUserNotifications(@PathVariable String userId) {

        System.out.println(userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Obtém todas as notificações do usuário
        List<Notification> notifications = notificationRepository.findByUser(user);

        // Filtra notificações que não foram lidas, aceitas ou recusadas
        return notifications.stream()
                .filter(notification -> !notification.isRead())
                .toList();
    }

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<Void> markAsRead(
            @PathVariable String userId,
            @PathVariable String notificationId) {

        notificationService.markNotificationAsRead(userId, notificationId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{notificationId}/accept")
    public ResponseEntity<Void> acceptNotification(
            @PathVariable String userId,
            @PathVariable String notificationId) {

        try {

            System.out.println("passou");
            // Adiciona o usuário ao time
            notificationService.acceptNotification(notificationId, userId);


            System.out.println("passou");
            // Notifica o remetente de que o convite foi aceito
            notificationService.sendResponseNotification(notificationId, userId, "accepted");

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Recusa uma notificação de convite para o time.
     * Uma notificação é enviada ao remetente informando que o convite foi recusado.
     *
     * @param userId         ID do usuário que está recusando
     * @param notificationId ID da notificação
     * @return ResponseEntity com status de sucesso
     */
    @PutMapping("/{notificationId}/reject")
    public ResponseEntity<Void> rejectNotification(
            @PathVariable String userId,
            @PathVariable String notificationId) {

        try {
            // Notifica o remetente de que o convite foi recusado
            notificationService.sendResponseNotification(notificationId, userId, "rejected");

            Notification notification = notificationRepository.findById(notificationId)
                    .orElseThrow(() -> new RuntimeException("Notificação não encontrada: " + notificationId));

            notification.setRead(true); // Marca a notificação como lida
            notificationRepository.save(notification); // Salva a atualização no banco de dados

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
