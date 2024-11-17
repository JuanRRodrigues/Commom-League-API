package br.com.jrr.apiTest.enums.adress;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum State {
    // Brasil
    SAO_PAULO("São Paulo", Country.BRAZIL),
    RIO_DE_JANEIRO("Rio de Janeiro", Country.BRAZIL),
    MINAS_GERAIS("Minas Gerais", Country.BRAZIL),
    BAHIA("Bahia", Country.BRAZIL),
    PARANA("Paraná", Country.BRAZIL),

    // Estados Unidos
    CALIFORNIA("California", Country.UNITED_STATES),
    TEXAS("Texas", Country.UNITED_STATES),
    FLORIDA("Florida", Country.UNITED_STATES),
    NEW_YORK("New York", Country.UNITED_STATES),
    ILLINOIS("Illinois", Country.UNITED_STATES),

    // Japão
    TOKYO("Tokyo", Country.JAPAN),
    OSAKA("Osaka", Country.JAPAN),
    HOKKAIDO("Hokkaido", Country.JAPAN),
    KYOTO("Kyoto", Country.JAPAN),

    // França
    ILE_DE_FRANCE("Île-de-France", Country.FRANCE),
    AUVERGNE_RHONE_ALPES("Auvergne-Rhône-Alpes", Country.FRANCE),

    // Coreia do Sul
    SEOUL("Seoul", Country.SOUTH_KOREA),
    GYEONGGI("Gyeonggi", Country.SOUTH_KOREA),
    BUSAN("Busan", Country.SOUTH_KOREA),

    // Rússia
    MOSCOW("Moscow", Country.RUSSIA),
    SAINT_PETERSBURG("Saint Petersburg", Country.RUSSIA),

    // Alemanha
    BAVARIA("Bavaria", Country.GERMANY),
    BERLIN("Berlin", Country.GERMANY),
    HAMBURG("Hamburg", Country.GERMANY),

    // China
    BEIJING("Beijing", Country.CHINA),
    SHANGHAI("Shanghai", Country.CHINA),
    GUANGDONG("Guangdong", Country.CHINA);

    private final String name;
    private final Country country;

    State(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return name;
    }

    @JsonCreator
    public static State fromString(String value) {
        for (State state : values()) {
            if (state.name().equalsIgnoreCase(value.replace(" ", "_"))) { // Permite tratar valores como "Minas Gerais"
                return state;
            }
        }
        throw new IllegalArgumentException("Unknown state: " + value);
    }
}
