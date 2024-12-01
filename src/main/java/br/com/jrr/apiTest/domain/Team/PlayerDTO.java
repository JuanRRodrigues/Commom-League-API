package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.domain.user.Entity.User;

public class PlayerDTO {
    private String id;
    private String userName;
    private String fullName;
    private String image;

    // Construtores, getters e setters
    public PlayerDTO(String id, String userName, String fullName, String image) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.image = image;
    }

    // Método estático para mapear o usuário para o PlayerDTO
    public static PlayerDTO mapUserToPlayerDTO(User player) {
        return new PlayerDTO(
                player.getId(),
                player.getUsername(),
                player.getFullName(),
                player.getImage()  // Adaptação conforme a estrutura de seu 'User'
        );
    }

    // Getters e setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
