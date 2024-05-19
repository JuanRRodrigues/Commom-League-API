package br.com.jrr.apiTest.controller;

import br.com.jrr.apiTest.domain.user.UserRole;

import java.time.LocalDate;
import java.util.Date;

public record RegisterDTO(String login, String password, UserRole role, String telefone, LocalDate birthDate, String cpf, String fullName, Double saldo  ) {







}
