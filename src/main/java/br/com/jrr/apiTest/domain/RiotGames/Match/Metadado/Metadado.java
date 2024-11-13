package br.com.jrr.apiTest.domain.RiotGames.Match.Metadado;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;


@Entity
@Table(name = "GameMetadado")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadado {


        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;

        @JsonAlias("matchId")
        private String matchId;


        @JsonAlias("dataVersion")
        private String dataVersion;

        @ElementCollection
        @JsonAlias("participants")
        private List<String> participants;

        public Metadado() {
        }

        public Metadado(String matchId, String dataVersion, String[] participants) {
                this.matchId = matchId;
                this.dataVersion = dataVersion;
                this.participants = List.of(participants);
        }

        public void setMatchId(String matchId) {
                this.matchId = matchId;
        }

        public void setDataVersion(String dataVersion) {
                this.dataVersion = dataVersion;
        }

        public void setParticipants(String[] participants) {
                this.participants = List.of(participants);
        }

        public String getId() {
                return id;
        }

        public String getMatchId() {
                return matchId;
        }

        public String getDataVersion() {
                return dataVersion;
        }

        public List<String> getParticipants() {
                return participants;
        }
}
