package br.com.jrr.apiTest.enums;

public enum LeagueRoute {
    TOP(1, "Top Lane"),
    MID(2, "Mid Lane"),
    BOT(3, "Bot Lane"),
    JUNGLE(4, "Jungle"),
    SUPPORT(5, "Support");

    private final int id;               // ID associado
    private final String displayName;   // Nome amigável

    LeagueRoute(int id, String displayName) {
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
