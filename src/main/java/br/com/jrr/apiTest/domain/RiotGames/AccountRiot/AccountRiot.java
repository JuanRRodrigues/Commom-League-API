package br.com.jrr.apiTest.domain.RiotGames.AccountRiot;

import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.MatchListDTO;
import br.com.jrr.apiTest.domain.RiotGames.League.LeagueEntry;
import br.com.jrr.apiTest.domain.RiotGames.Match.Match;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.API.DataAccountAPI;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.DadosUpdateDTO;
import br.com.jrr.apiTest.domain.user.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Table(name = "accounts_riot")
@Entity(name = "accountRiots")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AccountRiot {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String puuid;

    @NotNull
    private String gameName;

    @NotNull
    private String tagLine;

    @NotNull
    private String accountId;

    @NotNull
    private String idRiot;

    @NotNull
    private String profileIconId;

    @NotNull
    private String revisionDate;

    @NotNull
    private String summonerLevel;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Relacionamento OneToMany com LeagueEntry
    @OneToMany(mappedBy = "accountRiot", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<LeagueEntry> leagueEntries = new ArrayList<>();

    // Relacionamento Many-to-Many com Match
    @ManyToMany(mappedBy = "accountRiot", fetch = FetchType.EAGER)
    private Set<Match> matches = new HashSet<>();

    // Construtor que inicializa a lista de LeagueEntry e Match
    public AccountRiot(DataAccountAPI data1, DataAccountAPI data2, Set<Match> matches, List<LeagueEntry> leagueEntries) {
        this.puuid = data1.puuid();
        this.gameName = data1.gameName();
        this.tagLine = data1.tagLine();
        this.accountId = data2.accountId();
        this.idRiot = data2.idRiot();
        this.profileIconId = data2.profileIconId();
        this.revisionDate = data2.revisionDate();
        this.summonerLevel = data2.summonerLevel();
        this.matches = matches != null ? matches : new HashSet<>();
        this.leagueEntries = leagueEntries != null ? leagueEntries : new ArrayList<>();
    }

    public AccountRiot() {
    }

    // Método para atualizar informações do AccountRiot com o DadosUpdateDTO
    public void updateAccountDTO(DadosUpdateDTO dados) {
        if (dados.gameName() != null) {
            this.gameName = dados.gameName();
        }
        if (dados.tagLine() != null) {
            this.tagLine = dados.tagLine();
        }
    }

    // Método para adicionar uma partida à lista
    public void addMatch(Match match) {
        matches.add(match);
        match.getAccountRiot().add(this); // Estabelece a relação bidirecional
    }

    // Método para adicionar várias partidas à lista
    public void addMatches(List<Match> matches) {
        for (Match match : matches) {
            addMatch(match); // Chama o método existente para adicionar cada match
        }
    }




    // Método para adicionar uma liga à lista
    public void addLeagueEntry(LeagueEntry leagueEntry) {
        leagueEntries.add(leagueEntry);
        leagueEntry.setAccountRiot(this); // Estabelece a relação bidirecional
    }

    // Método para adicionar várias ligas à lista
    public void addLeagueEntries(List<LeagueEntry> leagueEntries) {
        for (LeagueEntry leagueEntry : leagueEntries) {
            addLeagueEntry(leagueEntry); // Chama o método existente para adicionar cada leagueEntry
        }
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null && user.getAccountRiot() != this) {
            user.setAccountRiot(this);  // Atualiza o User com o AccountRiot
        }
    }

    @Override
    public String toString() {
        return "AccountRiot{" +
                "id=" + id +
                ", puuid='" + puuid + '\'' +
                ", gameName='" + gameName + '\'' +
                ", tagLine='" + tagLine + '\'' +
                ", leagueEntries=" + leagueEntries +
                '}';
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotNull String getPuuid() {
        return puuid;
    }

    public void setPuuid(@NotNull String puuid) {
        this.puuid = puuid;
    }

    public @NotNull String getGameName() {
        return gameName;
    }

    public void setGameName(@NotNull String gameName) {
        this.gameName = gameName;
    }

    public @NotNull String getTagLine() {
        return tagLine;
    }

    public List<LeagueEntry> getLeagueEntries() {
        return leagueEntries;
    }

    public void setLeagueEntries(List<LeagueEntry> leagueEntries) {
        this.leagueEntries = leagueEntries;
    }



    public void setTagLine(@NotNull String tagLine) {
        this.tagLine = tagLine;
    }

    public @NotNull String getAccountId() {
        return accountId;
    }

    public void setAccountId(@NotNull String accountId) {
        this.accountId = accountId;
    }

    public @NotNull String getIdRiot() {
        return idRiot;
    }

    public void setIdRiot(@NotNull String idRiot) {
        this.idRiot = idRiot;
    }

    public @NotNull String getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(@NotNull String profileIconId) {
        this.profileIconId = profileIconId;
    }

    public @NotNull String getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(@NotNull String revisionDate) {
        this.revisionDate = revisionDate;
    }

    public @NotNull String getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(@NotNull String summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public User getUser() {
        return user;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }
}
