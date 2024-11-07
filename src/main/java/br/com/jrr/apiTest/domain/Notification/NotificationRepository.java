package br.com.jrr.apiTest.domain.Notification;



import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, String> {
}
