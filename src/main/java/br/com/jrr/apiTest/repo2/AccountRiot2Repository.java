package br.com.jrr.apiTest.repo2;
import br.com.jrr.apiTest.domain.Player.AccountRiot;
import br.com.jrr.apiTest.domain.Player.AccountRiot2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRiot2Repository extends JpaRepository<AccountRiot2, UUID> {
}
