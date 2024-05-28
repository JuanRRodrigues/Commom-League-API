package br.com.jrr.apiTest.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);

    Optional<User> findById(String id);

}
