package br.com.jrr.apiTest.domain.RiotGames.AccountRiot;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AccountRiotRepository extends JpaRepository<AccountRiot, String> {

    // Método para buscar uma conta pelo puuid
    AccountRiot findByPuuid(String puuid);

    // Busca parcial (LIKE) por gameName e tagLine
    Optional<AccountRiot> findByGameNameContainingAndTagLineContaining(String gameName, String tagLine);

    // Busca parcial por gameName e tagLine usando LIKE (com a correspondência parcial)
    List<AccountRiot> findByGameNameContainingIgnoreCaseAndTagLineContainingIgnoreCase(String gameName, String tagLine);

    // Busca apenas por gameName
    List<AccountRiot> findByGameNameContaining(String gameName);

    // Busca apenas por tagLine
    List<AccountRiot> findByTagLineContaining(String tagLine);

    // Busca pelo gameName, de forma parcial (com LIKE) e sem diferenciar maiúsculas/minúsculas
    List<AccountRiot> findByGameNameContainingIgnoreCase(String gameName);

    // Busca pelo tagLine, de forma parcial (com LIKE) e sem diferenciar maiúsculas/minúsculas
    List<AccountRiot> findByTagLineContainingIgnoreCase(String tagLine);


}
