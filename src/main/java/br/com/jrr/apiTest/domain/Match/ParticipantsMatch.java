package br.com.jrr.apiTest.domain.Match;

import br.com.jrr.apiTest.domain.API.DataMatchAPI;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;



@Entity
@Getter
@Table(name = "MatchParticipants")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantsMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("baronKills")
    private String baronKills;

  //  @JsonAlias("challegens")
  //  private Challenges chalenge;


    @JsonAlias("champLevel")
    private int champLevel;

    @JsonAlias("championId")
    private int championId;

    @JsonAlias("championName")
    private String chapionName;

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

  //  @ManyToOne
  //  @JoinColumn(name = "match_info_id", referencedColumnName = "id")
  //  private MatchInfoEntitty match;

    public ParticipantsMatch(DataMatchAPI dataMatchAPI) {
    }

    public ParticipantsMatch() {

    }

    public ParticipantsMatch(Long id, String baronKills, int champLevel, int championId, String chapionName, int deaths, int doubleKills, int dragonKills, boolean gameEndedInSurrender, String individualPosition, String puuid, String quadraKills, String riotIdGameName, String riotIdTagline, String tripleKills, Boolean win) {
        this.id = id;
        this.baronKills = baronKills;
        this.champLevel = champLevel;
        this.championId = championId;
        this.chapionName = chapionName;
        this.deaths = deaths;
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

    @Override
    public String toString() {
        return "ParticipantsMatch{" +
                "id=" + id +
                ", baronKills='" + baronKills + '\'' +
                ", champLevel=" + champLevel +
                ", championId=" + championId +
                ", chapionName='" + chapionName + '\'' +
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
}
