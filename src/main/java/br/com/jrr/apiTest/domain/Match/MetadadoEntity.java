package br.com.jrr.apiTest.domain.Match;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;


@Entity
@Table(name = "GameMetadado")
@Data
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadadoEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) //
        private long id ;

        @Getter
        @JsonAlias("matchId")
        private String matchId;

        @Getter
        @JsonAlias("dataVersion")
        private String dataVersion;

        @ElementCollection
        private List<String> participants;

        public MetadadoEntity() {
        }

        public MetadadoEntity(String matchId, String dataVersion, String[] participants) {
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


}
