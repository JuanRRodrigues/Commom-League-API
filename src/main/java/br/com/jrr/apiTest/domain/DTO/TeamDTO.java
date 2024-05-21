package br.com.jrr.apiTest.domain.DTO;

import br.com.jrr.apiTest.domain.user.User;

import java.util.List;
import java.util.UUID;


public record TeamDTO(String id, String name, String logo, String game, double saldo, int wins, int loses, boolean inGame

                             ) {



}
