package br.com.jrr.apiTest.domain.RiotGames.Match.Participant;

import br.com.jrr.apiTest.domain.RiotGames.Match.Info.Info;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
@Entity
@Getter
@Table(name = "MatchParticipants")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonAlias("baronKills")
    private String baronKills;

    @JsonAlias("champLevel")
    private int champLevel;

    @JsonAlias("championId")
    private int championId;

    @JsonAlias("championName")
    private String championName;

    @JsonAlias("deaths")
    private int deaths;

    @JsonAlias("doubleKills")
    private int doubleKills;

    @JsonAlias("dragonKills")
    private int dragonKills;

    @JsonAlias("gameEndedInSurrender")
    private boolean gameEndedInSurrender;

    @JsonAlias("individualPosition")
    private String individualPosition;

    @JsonAlias("puuid")
    private String puuid;

    @JsonAlias("quadraKills")
    private String quadraKills;

    @JsonAlias("riotIdGameName")
    private String riotIdGameName;

    @JsonAlias("riotIdTagline")
    private String riotIdTagline;

    @JsonAlias("tripleKills")
    private String tripleKills;

    @JsonAlias("win")
    private Boolean win;

    @ManyToOne
    @JoinColumn(name = "info_id")
    @JsonBackReference
    private Info info;

    @JsonAlias("teamId")
    private String teamId;

    @JsonCreator
    public Participant(@JsonProperty("id") Long id,
                       @JsonProperty("baronKills") String baronKills,
                       @JsonProperty("champLevel") int champLevel,
                       @JsonProperty("championId") int championId,
                       @JsonProperty("championName") String championName,
                       @JsonProperty("deaths") int deaths,
                       @JsonProperty("doubleKills") int doubleKills,
                       @JsonProperty("dragonKills") int dragonKills,
                       @JsonProperty("gameEndedInSurrender") boolean gameEndedInSurrender,
                       @JsonProperty("individualPosition") String individualPosition,
                       @JsonProperty("puuid") String puuid,
                       @JsonProperty("quadraKills") String quadraKills,
                       @JsonProperty("riotIdGameName") String riotIdGameName,
                       @JsonProperty("riotIdTagline") String riotIdTagline,
                       @JsonProperty("tripleKills") String tripleKills,
                       @JsonProperty("win") Boolean win,
             //         "assists": 10,
             //                      "champLevel": 17,
                 //                  "goldEarned": 13728,,
                //                   "item0": 1056,
                 //                  "item1": 3157,
                 //                  "item2": 3152,
                 //                  "item3": 3020,
                 //                  "item4": 3137,
                 //                  "item5": 6653,
                    //               "item6": 3340,
                    //               "kills": 3,
                     //  summoner1Id,
                    //   summoner2Id,

                       @JsonProperty("teamId") String teamId,
                       @JsonProperty("info") Info info) {
        this.id = id;
        this.baronKills = baronKills;
        this.champLevel = champLevel;
        this.championId = championId;
        this.championName = championName;
        this.deaths = deaths;
        this.teamId = teamId;
        this.doubleKills = doubleKills;
        this.dragonKills = dragonKills;
        this.gameEndedInSurrender = gameEndedInSurrender;
        this.individualPosition = individualPosition;
        this.puuid = puuid;
        this.quadraKills = quadraKills;
        this.riotIdGameName = riotIdGameName;
        this.riotIdTagline = riotIdTagline;
        this.tripleKills = tripleKills;
        this.win = win;
    }

    public Participant() {

    }
    @Override
    public String toString() {
        return "ParticipantsMatch{" +
                "id=" + id +
                ", baronKills='" + baronKills + '\'' +
                ", champLevel=" + champLevel +
                ", championId=" + championId +
                ", championName='" + championName + '\'' +
                ", deaths=" + deaths +
                ", doubleKills=" + doubleKills +
                ", dragonKills=" + dragonKills +
                ", gameEndedInSurrender=" + gameEndedInSurrender +
                ", individualPosition='" + individualPosition + '\'' +
                ", puuid='" + puuid + '\'' +
                ", quadraKills='" + quadraKills + '\'' +
                ", riotIdGameName='" + riotIdGameName + '\'' +
                ", riotIdTagline='" + riotIdTagline + '\'' +
                ", tripleKills='" + tripleKills + '\'' +
                ", win=" + win +

                '}';
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Long getId() {
        return id;
    }

    public String getBaronKills() {
        return baronKills;
    }

    public int getChampLevel() {
        return champLevel;
    }

    public int getChampionId() {
        return championId;
    }

    public String getChampionName() {
        return championName;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getDoubleKills() {
        return doubleKills;
    }

    public int getDragonKills() {
        return dragonKills;
    }

    public boolean isGameEndedInSurrender() {
        return gameEndedInSurrender;
    }

    public String getIndividualPosition() {
        return individualPosition;
    }

    public String getPuuid() {
        return puuid;
    }

    public String getQuadraKills() {
        return quadraKills;
    }

    public String getRiotIdGameName() {
        return riotIdGameName;
    }

    public String getRiotIdTagline() {
        return riotIdTagline;
    }

    public String getTripleKills() {
        return tripleKills;
    }

    public Boolean getWin() {
        return win;
    }

    public Info getInfo() {
        return info;
    }

    public Boolean getGameEndedInSurrender() {
        return gameEndedInSurrender;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}