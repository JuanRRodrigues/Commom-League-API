package br.com.jrr.apiTest.enums;

public enum SummonerSpell {
    FLASH(1, "Flash"),
    HEAL(2, "Heal"),
    IGNITE(3, "Ignite"),
    TELEPORT(4, "Teleport"),
    BARRIER(5, "Barrier"),
    EXHAUST(6, "Exhaust"),
    CLEANSE(7, "Cleanse"),
    GHOST(8, "Ghost"),
    SMITE(9, "Smite"),
    CLAIRVOYANCE(10, "Clairvoyance");

    private final int id;
    private final String displayName;

    SummonerSpell(int id, String displayName) {
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
