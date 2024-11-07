package br.com.jrr.apiTest.domain.Notification;

import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {


    private final UserRepository userRepository;

    public NotificationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createNotification(String userId, NotificationType type, String message) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Notification notification = new Notification(type, message, LocalDateTime.now(), false);
        user.addNotification(notification);
        userRepository.save(user);
    }

    public List<Notification> getUserNotifications(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getNotifications();
    }

    public void markNotificationAsRead(String userId, String notificationId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Notification notification = user.getNotifications().stream()
                .filter(n -> n.getId().equals(notificationId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        userRepository.save(user); // Persistir a alteração
    }
}
