package br.com.jrr.apiTest.controller;

import br.com.jrr.apiTest.domain.Account.AccountRiot;
import br.com.jrr.apiTest.domain.Team.Team;
import br.com.jrr.apiTest.domain.user.UserRole;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;

public record UserDTO(
        String id,
        String login,
        String password,
        String fullName,
        String cpf,
        String telefone,
        UserRole role,
        double saldo,
        LocalDate birthDate,
        Team team,
        AccountRiot accountRiot) {

    // Se necessário, adicione métodos customizados aqui
}

