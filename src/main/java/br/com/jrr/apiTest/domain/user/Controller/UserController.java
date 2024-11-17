package br.com.jrr.apiTest.domain.user.Controller;


import br.com.jrr.apiTest.domain.file.model.FileService;
import br.com.jrr.apiTest.domain.file.model.FileUploadRequest;
import br.com.jrr.apiTest.domain.file.model.FileUploadResponse;
import br.com.jrr.apiTest.domain.user.DTO.UserDTO;
import br.com.jrr.apiTest.domain.user.Entity.User;
import br.com.jrr.apiTest.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserRepository repository;

    @Autowired
    private FileService service;

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



}


