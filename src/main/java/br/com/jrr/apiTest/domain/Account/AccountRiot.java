package br.com.jrr.apiTest.domain.Account;


import br.com.jrr.apiTest.domain.API.DataAccountAPI;
import br.com.jrr.apiTest.domain.DTO.DadosUpdateDTO;
import br.com.jrr.apiTest.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Entity(name = "accountRiot")
@Getter
@Table(name = "accounstRiot")

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

    @ElementCollection
    private List<String> idMatchList;

    @OneToOne
    @JoinColumn(name = "account_riot_id")
    private User user;



    public AccountRiot(DataAccountAPI dataMediaAPI) {
        this.puuid = dataMediaAPI.puuid();
        this.gameName = dataMediaAPI.gameName();
        this.tagLine = dataMediaAPI.tagLine();
    }


    public void addIdMatches(List<String> idMatches) {
        this.idMatchList.addAll(idMatches);
    }

    public AccountRiot() {

    }
    public void setIdMatchList(List<String> idMatchList) {
        this.idMatchList = idMatchList;
    }


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
                ", idMatchList=" + idMatchList +
                '}';
    }
}
