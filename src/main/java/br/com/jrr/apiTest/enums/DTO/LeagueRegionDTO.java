package br.com.jrr.apiTest.enums.DTO;

public class LeagueRegionDTO {
    private String name;      // Representa o apiCode como nome
    private String language;  // Representa o displayName como nome amigável

    // Construtor do DTO
    public LeagueRegionDTO(String name, String language) {
        this.name = name;
        this.language = language;
    }

    // Getter e Setter para 'name' (código da API)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter e Setter para 'language' (nome amigável)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
