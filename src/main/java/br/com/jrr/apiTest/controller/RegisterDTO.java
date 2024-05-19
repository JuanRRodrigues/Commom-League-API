package br.com.jrr.apiTest.controller;

import br.com.jrr.apiTest.domain.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {



}
