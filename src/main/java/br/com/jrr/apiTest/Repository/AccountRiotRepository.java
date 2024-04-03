package br.com.jrr.apiTest.Repository;
import br.com.jrr.apiTest.domain.Player.AccountRiot;
import br.com.jrr.apiTest.domain.Player.AccountRiotDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRiotRepository extends JpaRepository<AccountRiot, UUID> {
}
