package br.com.jrr.apiTest.domain.user.Entity;
import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.AccountRiot;
import br.com.jrr.apiTest.domain.Notification.Notification;
import br.com.jrr.apiTest.domain.Team.Team;
import br.com.jrr.apiTest.domain.user.enums.UserRole;
import br.com.jrr.apiTest.enums.Language;
import br.com.jrr.apiTest.enums.LeagueRegion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name= "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    public String login;

    public String userName;

    private String password;

    private String fullName;

    private String cpf;

    private String telefone;

    private String country;

    private String state;

    private String city;

    private UserRole role;

    private Double saldo;

    private LocalDate birthDate;

    private String image;

    @Enumerated(EnumType.STRING)
    private LeagueRegion leagueRegion;

    @Enumerated(EnumType.STRING)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne
    @JoinColumn(name = "account_id")
    private AccountRiot accountRiot;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();


    public LeagueRegion getLeagueRegion() {
        return leagueRegion;
    }

    public void setLeagueRegion(LeagueRegion leagueRegion) {
        this.leagueRegion = leagueRegion;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
       else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public User(String login, String userName, String password, UserRole role, String telefone, LocalDate birthDate, String cpf, String fullName, Team team, Double saldo, AccountRiot accountRiot,String image,String country, String city, String state, Language language, LeagueRegion leagueRegion) {
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
        this.image = image;
        this.userName = userName;
        this.country = country;
        this.state = state;
        this.city = city;
        this.leagueRegion = leagueRegion;
        this.language = language;
    }

    public String getImage() {
        return image;
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
        notification.setUser(this); // Estabelece a relação bidirecional
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return userName;
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
        if (accountRiot != null && accountRiot.getUser() != this) {
            accountRiot.setUser(this);
        }
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Team getTeam() {
        return team;
    }

    public AccountRiot getAccountRiot() {
        return accountRiot;
    }


}
