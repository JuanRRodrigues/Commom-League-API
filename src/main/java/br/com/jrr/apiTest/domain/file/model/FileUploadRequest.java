package br.com.jrr.apiTest.domain.file.model;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadRequest {
    private String id;
    private MultipartFile file;
    private String name;

    public FileUploadRequest(String id, MultipartFile file, String name) {
        this.id = id;
        this.file = file;
        this.name = name;
    }

    public MultipartFile file() {
        return file;
    }

    public String name() {
        return name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getName() {
        return name;
    }
}
