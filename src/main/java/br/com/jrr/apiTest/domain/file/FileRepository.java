package br.com.jrr.apiTest.domain.file;

import br.com.jrr.apiTest.domain.file.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

}

