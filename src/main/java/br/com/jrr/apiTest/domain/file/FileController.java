package br.com.jrr.apiTest.domain.file;

import br.com.jrr.apiTest.domain.file.model.File;
import br.com.jrr.apiTest.domain.file.model.FileService;
import br.com.jrr.apiTest.domain.file.model.FileUploadRequest;
import br.com.jrr.apiTest.domain.file.model.FileUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FileUploadResponse> upload(
            @Validated @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name) throws IOException {

        FileUploadRequest fileUploadRequest = new FileUploadRequest(file, name);
        return ResponseEntity.ok(fileService.upload(fileUploadRequest));
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE) // Ajuste conforme o tipo de imagem
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        File fileEntity = fileService.findById(id); // Método que você deve implementar

        if (fileEntity != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Ajuste conforme necessário
                    .body(fileEntity.getData()); // Retorna os dados da imagem
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FileUploadResponse> updateImage(
            @PathVariable String id, // Recebe o id da imagem ou do usuário
            @RequestParam("file") MultipartFile file) throws IOException {

        // Criação de um objeto de solicitação para atualização de imagem
        FileUploadRequest fileUploadRequest = new FileUploadRequest(file, id);

        // Chama o serviço para atualizar a imagem
        FileUploadResponse response = fileService.update(id, fileUploadRequest);

        if (response != null) {
            return ResponseEntity.ok(response);  // Retorna o response com informações sobre o arquivo
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Se não encontrar o arquivo, retorna erro
        }
    }
}

