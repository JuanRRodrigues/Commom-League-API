package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.domain.user.User;
import br.com.jrr.apiTest.domain.user.UserDTO;

import java.util.List;
import java.util.UUID;


public record TeamRegistreDTO(String name, String game, String idUser,String idTeam) {

}
