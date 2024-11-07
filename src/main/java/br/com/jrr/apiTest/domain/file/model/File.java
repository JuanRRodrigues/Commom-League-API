package br.com.jrr.apiTest.domain.file.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "files")
@Entity(name = "file")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class File {

    @Id
    private String id;

    @Column(unique = true)
    private String name;

    private long size;

    @Lob
    private byte[] data;

    public File(String id, String name, long size, byte[] data) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.data = data;
    }

    public File(String id, String name, long size) {
        this(id, name, size, null);
    }

    // Métodos getter e setter para data
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
