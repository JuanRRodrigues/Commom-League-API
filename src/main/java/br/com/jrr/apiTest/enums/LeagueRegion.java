package br.com.jrr.apiTest.enums;

public enum LeagueRegion {
    BR1(1, "Brazil", "BR1"),
    NA1(2, "North America", "NA1"),
    EUW1(3, "Europe West", "EUW1"),
    EUNE1(4, "Europe Nordic & East", "EUNE1"),
    KR(5, "Korea", "KR"),
    JP1(6, "Japan", "JP1"),
    LAN1(7, "Latin America North", "LAN1"),
    LAS1(8, "Latin America South", "LAS1"),
    OCE1(9, "Oceania", "OCE1"),
    RU(10, "Russia", "RU"),
    TR1(11, "Turkey", "TR1");

    private final int id;               // ID associado
    private final String displayName;   // Nome amigável
    private final String apiCode;        // Código da API

    LeagueRegion(int id, String displayName, String apiCode) {
        this.id = id;
        this.displayName = displayName;
        this.apiCode = apiCode;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getApiCode() {
        return apiCode;
    }
}
