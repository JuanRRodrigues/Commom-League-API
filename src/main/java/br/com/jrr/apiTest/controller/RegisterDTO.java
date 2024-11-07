package br.com.jrr.apiTest.controller;

import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.AccountRiot;
import br.com.jrr.apiTest.domain.Team.Team;
import br.com.jrr.apiTest.domain.user.enums.UserRole;
import br.com.jrr.apiTest.enums.Language;
import br.com.jrr.apiTest.enums.LeagueRegion;

import java.time.LocalDate;

public record RegisterDTO(String login, String userName, String password, UserRole role, String telefone, LocalDate birthDate, String cpf, String fullName,
                          Team team, String image, AccountRiot accountRiot, Double saldo, String city, String Country, String state, Language language,
                          LeagueRegion leagueRegion) {







}
