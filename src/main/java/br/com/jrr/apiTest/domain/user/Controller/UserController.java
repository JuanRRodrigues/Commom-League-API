package br.com.jrr.apiTest.domain.user.Controller;


import br.com.jrr.apiTest.domain.file.model.FileService;
import br.com.jrr.apiTest.domain.file.model.FileUploadRequest;
import br.com.jrr.apiTest.domain.file.model.FileUploadResponse;
import br.com.jrr.apiTest.domain.user.DTO.SearchUserDTO;
import br.com.jrr.apiTest.domain.user.DTO.UserDTO;
import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserRepository repository;

    @Autowired
    private FileService service;

    @Autowired
    private UserService userService;

    public UserController(FileService service){
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FileUploadResponse> upload(
            @Validated @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name
    ) throws IOException {
        FileUploadRequest fileUploadRequest = new FileUploadRequest(null, file, name);
        return ResponseEntity.ok(service.upload(fileUploadRequest));

    }

    @GetMapping("/without-team")
    public ResponseEntity<List<SearchUserDTO>> getUsersWithoutTeam() {
        // Filtra os usuários sem time
        List<SearchUserDTO> usersWithoutTeam = repository.findAll()
                .stream()
                .filter(user -> user.getTeam() == null) // Considerando que existe um campo `team` no User
                .map(user -> new SearchUserDTO(user.getId(), user.getUsername(), user.getImage())) // Mapeia para o SearchUserDTO
                .collect(Collectors.toList());
        return ResponseEntity.ok(usersWithoutTeam);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchUserDTO>> searchUsers(@RequestParam(required = false) String userName) {
        // Log para depuração
        System.out.println("Searching for userName: " + userName);

        // Chama o serviço de busca de usuários
        List<User> users = userService.searchUsers(userName);

        // Se não encontrar nenhum usuário
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Converte a lista de User para SearchUserDTO
        List<SearchUserDTO> userDTOs = users.stream()
                .map(user -> new SearchUserDTO(user.getId(), user.getUsername(), user.getImage()))  // Supondo que User tem getId(), getName() e getImage()
                .collect(Collectors.toList());

        // Retorna a lista de SearchUserDTO
        return ResponseEntity.ok(userDTOs);
    }



}


