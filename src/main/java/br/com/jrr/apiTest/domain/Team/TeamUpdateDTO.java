package br.com.jrr.apiTest.domain.Team;

public class TeamUpdateDTO {

    private String name;     // Nome do time
    private String logo;     // Logo do time
    private String game;     // Jogo do time
    private Boolean inGame;  // Status de se o time está no jogo

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Boolean getInGame() {
        return inGame;
    }

    public void setInGame(Boolean inGame) {
        this.inGame = inGame;
    }
}
