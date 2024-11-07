package br.com.jrr.apiTest.enums;

public enum LeagueRank {
    IRON("Iron", 1, "Iron"),
    BRONZE("Bronze", 2, "Bronze"),
    SILVER("Silver", 3, "Silver"),
    GOLD("Gold", 4, "Gold"),
    PLATINUM("Platinum", 5, "Platinum"),
    DIAMOND("Diamond", 6, "Diamond"),
    MASTER("Master", 7, "Master"),
    GRANDMASTER("Grandmaster", 8, "Grandmaster"),
    CHALLENGER("Challenger", 9, "Challenger");

    private final String name;
    private final int level;
    private final String id;

    LeagueRank(String name, int level, String id) {
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

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
