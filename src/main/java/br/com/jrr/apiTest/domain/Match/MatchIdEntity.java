package br.com.jrr.apiTest.domain.Match;


import br.com.jrr.apiTest.domain.API.IdMatchAPI;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "GameMatch")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type_entity", discriminatorType = DiscriminatorType.STRING)
public class MatchIdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private IdMatch idMatch;
    public MatchIdEntity(IdMatchAPI data) {
       // this.idMatch = (IdMatch) data.getIdMatch().getIdmatche();

    }
    public MatchIdEntity() {

    }

}
