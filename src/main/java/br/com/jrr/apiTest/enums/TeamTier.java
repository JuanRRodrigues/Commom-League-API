package br.com.jrr.apiTest.enums;

public enum TeamTier {
    ASPIRANT("Aspirant", 1, 1),
    VALIANT("Valiant", 2, 2),
    HEROIC("Heroic", 3, 3),
    LEGENDARY("Legendary", 4, 4),
    EPIC("Epic", 5, 5);

    private final String name; // Nome do tier
    private final int level;    // Nível numérico do tier
    private final int id;       // ID do tier

    TeamTier(String name, int level, int id) {
        this.name = name;
        this.level = level;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
