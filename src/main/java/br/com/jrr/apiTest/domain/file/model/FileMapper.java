package br.com.jrr.apiTest.domain.file.model;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FileMapper {

    public File toEntity(String id, FileUploadRequest fileUploadRequest) throws IOException {
        return new File(
                null,
                fileUploadRequest.name(),
                fileUploadRequest.file().getSize(),
                fileUploadRequest.file().getBytes() // Adicione os bytes da imagem
        );
    }

    public FileUploadResponse toFileUploadResponse(File fileEntity) {
        return new FileUploadResponse(
                fileEntity.getId(),
                fileEntity.getName(),
                fileEntity.getSize()
        );
    }
}
