package br.com.jrr.apiTest.domain.UserMercadoPago;

import java.time.OffsetDateTime;

public class CreateUserTeste {

    private int id;
    private String nickname;
    private String password;
    private String siteStatus;
    private String siteId;
    private String description;
    private String email;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateLastUpdated;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSiteStatus() {
        return siteStatus;
    }

    public void setSiteStatus(String siteStatus) {
        this.siteStatus = siteStatus;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getDateLastUpdated() {
        return dateLastUpdated;
    }

    public void setDateLastUpdated(OffsetDateTime dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }
}
