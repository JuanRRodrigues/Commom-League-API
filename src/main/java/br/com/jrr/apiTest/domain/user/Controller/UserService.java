package br.com.jrr.apiTest.domain.user.Controller;

import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Repositório que acessa o banco de dados

    public List<User> searchUsers(String userName) {
        if (userName == null || userName.isEmpty()) {
            return userRepository.findAllByTeamIsNull(); // Retorna todos os usuários sem time se não houver nome de busca
        } else {
            // Busca por nome de usuário contendo a string informada (caso insensível) e sem time
            return userRepository.findByUserNameContainingIgnoreCaseAndTeamIsNull(userName);
        }
    }
}
