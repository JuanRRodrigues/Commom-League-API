package br.com.jrr.apiTest.domain.Notification;



import br.com.jrr.apiTest.domain.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, String> {
    List<Notification> findByUser(User user);
}
