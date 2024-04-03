package br.com.jrr.apiTest.domain.Player;


import br.com.jrr.apiTest.domain.API.DataMediaAPI;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@Table(name = "accountRiot2")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type_entity", discriminatorType = DiscriminatorType.STRING)

public class AccountRiot2 {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String puuid;

    @NotNull
    private String gameName;

    @NotNull
    private String tagLine;



    public AccountRiot2(DataMediaAPI dataMediaAPI) {
        this.puuid = dataMediaAPI.puuid();
        this.gameName = dataMediaAPI.gameName();
        this.tagLine = dataMediaAPI.tagLine();
    }

    public AccountRiot2() {

    }



}
