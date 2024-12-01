package br.com.jrr.apiTest.domain.Notification;

import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.Team.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Table(name = "notification")
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String message;

    private LocalDateTime timestamp;

    private boolean isRead;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // User receiving the notification

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender; // User who sent the notification (e.g., team leader)

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team; // Team related to the notification, if applicable

    // Constructors
    public Notification(NotificationType type, String message, LocalDateTime timestamp, boolean isRead, User user, User sender, Team team) {
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
        this.isRead = false;
        this.user = user;
        this.sender = sender;
        this.team = team;
    }

    public Notification(NotificationType type, String message, LocalDateTime timestamp, boolean isRead, Team team, User player, User sender) {
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
        this.isRead = isRead;
    }

    // Construtor padrão (sem parâmetros)
    public Notification() {
    }

    public Notification(User sender, String userId, String message, String status) {
    }

    public Notification(NotificationType notificationType, String message, LocalDateTime now, User player, Team team, boolean b) {
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
