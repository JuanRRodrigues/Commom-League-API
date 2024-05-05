package br.com.jrr.apiTest.domain.Match;


import br.com.jrr.apiTest.domain.API.DataMatchAPI;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "GameMatch")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type_entity", discriminatorType = DiscriminatorType.STRING)
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matchId;
    private String gameMode;

    @OneToOne(cascade = CascadeType.ALL)
    private MatchInfoEntitty info;

    @OneToOne(cascade = CascadeType.ALL)
    private MetadadoEntity metadado;

    public MatchEntity(DataMatchAPI dataMatchAPI) {
        if (dataMatchAPI != null) {
            this.matchId = dataMatchAPI.getInfo().getGameId();
            this.gameMode = dataMatchAPI.getInfo().getGameMode();
            this.info = dataMatchAPI.getInfo();
            this.metadado = dataMatchAPI.getMetadado();
        }
    }

    public MatchEntity() {

    }

    @Override
    public String toString() {
        return "MatchEntity{" +
                "id=" + id +
                ", matchId='" + matchId + '\'' +
                ", gameMode='" + gameMode + '\'' +
                ", info=" + info +
                ", metadado=" + metadado +
                '}';
    }
}
