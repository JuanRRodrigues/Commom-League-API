package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.domain.user.DTO.LeaderDTO;

import br.com.jrr.apiTest.domain.user.Entity.User;

import java.util.ArrayList;
import java.util.List;

// DTO para o Time
public class TeamDTO {
    private String id;
    private String name;
    private String logo;
    private int wins;
    private int loses;
    private boolean inGame;
    private LeaderDTO leader;
    private List<PlayerDTO> players;  // Lista de jogadores como PlayerDTO

    // Construtores, getters e setters
    public TeamDTO(String id, String name, String logo, int wins, int loses, boolean inGame, LeaderDTO leader, List<PlayerDTO> players) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.wins = wins;
        this.loses = loses;
        this.inGame = inGame;
        this.leader = leader;
        this.players = players;
    }

    // Método para mapear usuários para PlayerDTOs
    public static List<PlayerDTO> mapUsersToPlayerDTOs(List<User> players) {
        List<PlayerDTO> playerDTOs = new ArrayList<>();
        for (User player : players) {
            playerDTOs.add(PlayerDTO.mapUserToPlayerDTO(player));  // Mapeia cada usuário para PlayerDTO
        }
        return playerDTOs;
    }

    // Método para mapear o líder para LeaderDTO
    public static LeaderDTO mapUserToLeaderDTO(User leader) {
        return new LeaderDTO(
                leader.getId(),
                leader.getUsername(),
                leader.getFullName(),
                leader.getImage()  // Adaptação conforme a estrutura de seu 'User'
        );
    }

    // Getters e setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }
    public int getWins() { return wins; }
    public void setWins(int wins) { this.wins = wins; }
    public int getLoses() { return loses; }
    public void setLoses(int loses) { this.loses = loses; }
    public boolean isInGame() { return inGame; }
    public void setInGame(boolean inGame) { this.inGame = inGame; }
    public LeaderDTO getLeader() { return leader; }
    public void setLeader(LeaderDTO leader) { this.leader = leader; }
    public List<PlayerDTO> getPlayers() { return players; }
    public void setPlayers(List<PlayerDTO> players) { this.players = players; }
}
