package br.com.jrr.apiTest.domain.Match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;


@Entity
@Table(name = "idMatch")
@Data
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdMatch {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) //
        private long id ;

        @ElementCollection
        private List<String> Idmatche;

        public IdMatch() {
        }

        public IdMatch(String[] Idmatche) {
                this.Idmatche = List.of(Idmatche);
        }



}
