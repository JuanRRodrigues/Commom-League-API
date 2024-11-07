package br.com.jrr.apiTest.domain.RiotGames.AccountRiot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRiotRepository extends JpaRepository<AccountRiot, String> {

    AccountRiot findByPuuid(String puuid);
}
