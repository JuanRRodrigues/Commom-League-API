package br.com.jrr.apiTest.domain.RiotGames.AccountRiot;


import br.com.jrr.apiTest.domain.RiotGames.Match.Match;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.API.DataAccountAPI;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.DadosUpdateDTO;
import br.com.jrr.apiTest.domain.user.Entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

@Table(name= "accounst_riot")
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


   // @OneToMany(mappedBy = "accountRiot", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  //  private List<Match> matchList = new ArrayList<>();


    @NotNull
    private String accountId;

  //  public List<Match> getMatchList() {
   //     return matchList;
 //   }

   // public void setMatchList(List<Match> matchList) {
//        this.matchList = matchList;
//    }

    @NotNull
    private String idRiot;

    @NotNull
    private String profileIconId;

    @NotNull
    private String revisionDate;

    @NotNull
    private String summonerLevel;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public AccountRiot(DataAccountAPI data1, DataAccountAPI data2, List<Match> matchList) {
        this.puuid = data1.puuid();
        this.gameName = data1.gameName();
        this.tagLine = data1.tagLine();
        this.accountId = data2.accountId();
        this.idRiot = data2.idRiot();
        this.profileIconId = data2.profileIconId();
        this.revisionDate = data2.revisionDate();
        this.summonerLevel = data2.summonerLevel();
      //  this.matchList = matchList;
    }





  //  public void addMatch(Match match) {
     //   matchList.add(match);
       // match.setAccountRiot(this); // Estabelece a relação bidirecional
  //  }

 //   public void addMatches(List<Match> matches) {
  //      for (Match match : matches) {
  //          addMatch(match); // Chama o método existente para adicionar cada match
  //      }
  //  }

    public AccountRiot() {

    }

   // public void setIdMatchList(List<Match> idMatchList) {
    //    this.matchList = idMatchList;
 //   }

    public void UpdateAccountDTO(DadosUpdateDTO dados) {
        if(dados.gameName() != null){
            this.gameName = dados.gameName();
        }
        if(dados.tagLine() != null){
            this.tagLine = dados.tagLine();
        }

    }


    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AccountRiot{" +
                "id=" + id +
                ", puuid='" + puuid + '\'' +
                ", gameName='" + gameName + '\'' +
                ", tagLine='" + tagLine + '\'' +
          //      ", idMatchList=" + matchList +
                '}';
    }


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


}
