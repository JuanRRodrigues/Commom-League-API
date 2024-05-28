package br.com.jrr.apiTest.domain.user;

import br.com.jrr.apiTest.domain.Account.AccountRiot;
import br.com.jrr.apiTest.domain.DTO.AccountRiotDTO;

import java.time.LocalDate;

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
        br.com.jrr.apiTest.domain.DTO.AccountRiotDTO AccountRiot
        ) {




        public static UserDTO fromUser(br.com.jrr.apiTest.domain.user.User user) {
                if (user == null) {
                        // Retorne um DTO vazio ou lance uma exceção, dependendo do que é mais apropriado para o seu caso
                        return new UserDTO(null, null, null, null, null, null, null, 00.00, null, null);
                }

                return new UserDTO(
                        user.getId(),
                        user.getLogin(),
                        user.getPassword(),
                        user.getFullName(),
                        user.getCpf(),
                        user.getTelefone(),
                        user.getRole(),
                        user.getSaldo(),
                        user.getBirthDate(),
                        AccountRiotDTO.fromAccountRiot(user.getAccountRiot())
                );
        }

    // Se necessário, adicione métodos customizados aqui
}

