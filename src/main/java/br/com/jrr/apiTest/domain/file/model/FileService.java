package br.com.jrr.apiTest.domain.file.model;

import br.com.jrr.apiTest.domain.file.FileRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileService {

    private final FileRepository repository;
    private final FileMapper mapper;

    public FileService(FileRepository repository, FileMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public FileUploadResponse upload(FileUploadRequest fileUploadRequest) throws IOException {
        var fileEntity = mapper.toEntity(newID(), fileUploadRequest);
        repository.save(fileEntity);
        return mapper.toFileUploadResponse(fileEntity);
    }

    // Método de Update
    public FileUploadResponse update(String id, FileUploadRequest fileUploadRequest) throws IOException {
        // Encontra o arquivo existente
        Optional<File> optionalFile = repository.findById(id);

        // Verifica se o arquivo existe
        if (optionalFile.isPresent()) {
            File existingFile = optionalFile.get();

            // Substitui os dados do arquivo existente com os novos dados
            existingFile.setData(fileUploadRequest.getFile().getBytes()); // Atualiza os dados do arquivo (imagem)
            existingFile.setName(fileUploadRequest.getName()); // Atualiza o nome do arquivo se necessário

            // Salva o arquivo atualizado no banco de dados
            repository.save(existingFile);

            // Retorna a resposta com as informações do arquivo atualizado
            return mapper.toFileUploadResponse(existingFile);
        } else {
            // Caso o arquivo não exista, você pode lançar uma exceção ou retornar um erro mais informativo
            throw new RuntimeException("Arquivo não encontrado para atualização.");
        }
    }

    private String newID() {
        return java.util.UUID.randomUUID().toString();
    }

    public File findById(String id) {
        return repository.findById(id).orElse(null);
    }
}
