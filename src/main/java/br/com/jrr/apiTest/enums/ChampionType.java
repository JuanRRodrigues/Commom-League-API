package br.com.jrr.apiTest.enums;

public enum ChampionType {
    FIGHTER(1, "Fighter"),
    MAGE(2, "Mage"),
    ASSASSIN(3, "Assassin"),
    TANK(4, "Tank"),
    SUPPORT(5, "Support"),
    MARKSMAN(6, "Marksman");

    private final int id;
    private final String displayName;

    ChampionType(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
}
