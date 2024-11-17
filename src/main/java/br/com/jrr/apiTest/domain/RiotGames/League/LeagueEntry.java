package br.com.jrr.apiTest.domain.RiotGames.League;

import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.AccountRiot;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "league_entries")
@JsonIgnoreProperties(ignoreUnknown = true)  // Ignorar quaisquer campos desconhecidos durante a desserialização
public class LeagueEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonAlias("leagueId")
    private String leagueId;

    @JsonAlias("queueType")   // Mapeando 'queueType' no JSON para o campo 'queueType' da classe
    private String queueType;

    @JsonAlias("tier")        // Mapeando 'tier' no JSON para o campo 'tier' da classe
    private String tier;

    @JsonAlias("rank")        // Mapeando 'rank' no JSON para o campo 'elo' da classe
    private String elo;        // Alterei 'rank' para 'elo' (para refletir o nome do campo no banco de dados)

    private String leaguePoints;
    private String wins;
    private String losses;

    // Relacionamento com AccountRiot
    @ManyToOne
    @JoinColumn(name = "account_riot_id") // Nome da chave estrangeira no banco de dados
    @JsonIgnore  // Ignora a serialização do campo accountRiot
    private AccountRiot accountRiot;

    // Construtores, getters e setters
    public LeagueEntry() {
    }

    public LeagueEntry(String leagueId, String queueType, String tier, String elo, String leaguePoints, String wins, String losses) {
        this.leagueId = leagueId;
        this.queueType = queueType;
        this.tier = tier;
        this.elo = elo; // Usando 'elo' para o campo 'rank'
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getElo() {
        return elo;
    }

    public void setElo(String elo) {
        this.elo = elo;
    }

    public String getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(String leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public AccountRiot getAccountRiot() {
        return accountRiot;
    }

    public void setAccountRiot(AccountRiot accountRiot) {
        this.accountRiot = accountRiot;
    }

    @Override
    public String toString() {
        return "LeagueEntry{" +
                ", leagueId='" + leagueId + '\'' +
                ", queueType='" + queueType + '\'' +
                ", tier='" + tier + '\'' +
                ", elo='" + elo + '\'' +
                ", leaguePoints='" + leaguePoints + '\'' +
                ", wins='" + wins + '\'' +
                ", losses='" + losses + '\'' +
                '}';
    }
}
