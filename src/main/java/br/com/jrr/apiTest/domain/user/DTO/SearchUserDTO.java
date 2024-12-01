package br.com.jrr.apiTest.domain.user.DTO;

public class SearchUserDTO {
    private String id;
    private String name;
    private String image; // URL ou identificador da imagem

    public SearchUserDTO(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    // Getters e Setters
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
