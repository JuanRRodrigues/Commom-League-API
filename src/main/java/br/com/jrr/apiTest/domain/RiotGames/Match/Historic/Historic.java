package br.com.jrr.apiTest.domain.RiotGames.Match.Historic;


import br.com.jrr.apiTest.domain.RiotGames.Match.API.IdMatchAPI;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "GameMatch")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type_entity", discriminatorType = DiscriminatorType.STRING)
public class Historic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private IdMatch idMatch;

    public Historic(IdMatchAPI data) {
        this.idMatch = (IdMatch) data.getIdMatch();

    }
    public Historic() {

    }

}
