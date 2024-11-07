package br.com.jrr.apiTest.domain.Notification;

import br.com.jrr.apiTest.domain.Notification.NotificationType;

public class NotificationDto {
    private NotificationType type;
    private String message;

    // Constructor
    public NotificationDto(NotificationType type, String message) {
        this.type = type;
        this.message = message;
    }

    // Getters and Setters
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
}
