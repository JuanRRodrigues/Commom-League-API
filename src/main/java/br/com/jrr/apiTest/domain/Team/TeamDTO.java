package br.com.jrr.apiTest.domain.Team;

import br.com.jrr.apiTest.domain.user.DTO.LeaderDTO;
import br.com.jrr.apiTest.domain.user.DTO.UserDTO;

import java.util.List;


public record TeamDTO(String id, String name, String logo, String game, double saldo, int wins, int loses, boolean inGame, List<UserDTO> players, LeaderDTO leader

                             ) {



}
