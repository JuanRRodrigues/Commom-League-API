package br.com.jrr.apiTest.domain.Team;

import java.util.List;
public record TeamRegisterDTO(String name, String logo, List<String> players) {

}
