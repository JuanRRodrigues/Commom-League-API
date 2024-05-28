package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.domain.user.LeaderDTO;
import br.com.jrr.apiTest.domain.user.User;
import br.com.jrr.apiTest.domain.user.UserDTO;

import java.util.List;
import java.util.UUID;


public record TeamDTO(String id, String name, String logo, String game, double saldo, int wins, int loses, boolean inGame, List<UserDTO> players, LeaderDTO leader

                             ) {



}
