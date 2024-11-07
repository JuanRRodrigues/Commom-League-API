package br.com.jrr.apiTest.domain.Notification;

public enum NotificationType {
    CALL_TEAM("Call for the team"),
    TOURNAMENT_OPEN("Tournament of your rank is open"),
    FRIEND_REQUEST("Friend request"),
    CONFIRM_PARTICIPATION("Confirm participation in the tournament"),
    BALANCE_DEPOSIT("You have deposited funds into your account"),
    BALANCE_RECEIPT("Funds received");

    private final String description;

    NotificationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
