package br.com.jrr.apiTest.domain.user.repository;

import br.com.jrr.apiTest.domain.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);

    Optional<User> findById(String id);

    // Busca por nome de usuário que contenha a string informada (insensível a maiúsculas/minúsculas) e sem time
    List<User> findByUserNameContainingIgnoreCaseAndTeamIsNull(String userName);

    // Retorna todos os usuários sem time
    List<User> findAllByTeamIsNull();

    User findByUserName(String userName);
}
