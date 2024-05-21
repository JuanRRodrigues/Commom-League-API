package br.com.jrr.apiTest.domain.user;


import br.com.jrr.apiTest.domain.Account.AccountRiot;
import br.com.jrr.apiTest.domain.Team.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table(name= "users2")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    private String login;

    private String password;

    private String fullName;

    private String cpf;

    private String telefone;

    private UserRole role;

    private Double saldo;

    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne
    @JoinColumn(name = "account_riot_id")
    private AccountRiot accountRiot;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
       else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public User(String login, String password, UserRole role, String telefone, LocalDate birthDate, String cpf, String fullName, Team team, Double saldo, AccountRiot accountRiot) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.telefone = telefone;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.fullName = fullName;
        this.team = team;
        this.saldo = saldo;
        this.accountRiot = accountRiot;
    }


    @Override
    public String getUsername() {
        return login;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void setTeam(Team team) {
        this.team = team;
    }

    public void setAccountRiot(AccountRiot accountRiot) {
        this.accountRiot = accountRiot;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", role=" + role +
                ", saldo=" + saldo +
                ", birthDate=" + birthDate +
                ", team=" + team +
                ", accountRiot=" + accountRiot +
                '}';
    }



}
