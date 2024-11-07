package br.com.jrr.apiTest.domain.user.DTO;

import br.com.jrr.apiTest.domain.RiotGames.AccountRiot.DTO.AccountRiotDTO;
import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.enums.UserRole;
import br.com.jrr.apiTest.enums.Language;
import br.com.jrr.apiTest.enums.LeagueRegion;

import java.time.LocalDate;

public record UserDTO(
        String id,
        String login,
        String userName,
        String password,
        String fullName,
        String cpf,
        String telefone,
        UserRole role,
        double saldo,
        LocalDate birthDate,
        String image,
        String city,
        String country,
        String state,
        LeagueRegion leagueRegion,
        Language language,
        AccountRiotDTO AccountRiot
        ) {

        public static UserDTO fromUser(User user) {
                if (user == null) {

                        return new UserDTO(null, null, null,  null, null, null, null, null, 00.00,  null, null, null, null,null,null, null,null);
                }

                return new UserDTO(
                        user.getId(),
                        user.getLogin(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getFullName(),
                        user.getCpf(),
                        user.getTelefone(),
                        user.getRole(),
                        user.getSaldo(),
                        user.getBirthDate(),
                        user.getImage(),
                        user.getCity(),
                        user.getCountry(),
                        user.getState(),
                        user.getLeagueRegion(),
                        user.getLanguage(),
                        AccountRiotDTO.fromAccountRiot(user.getAccountRiot())
                );
        }
}

