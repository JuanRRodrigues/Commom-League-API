package br.com.jrr.apiTest.domain.Account;
import br.com.jrr.apiTest.domain.Account.AccountRiot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRiotRepository extends JpaRepository<AccountRiot, String> {

    AccountRiot findByPuuid(String puuid);
}
