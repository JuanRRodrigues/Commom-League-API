package br.com.jrr.apiTest.enums.adress;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum City {
    // Brasil
    SAO_PAULO_CITY("São Paulo", State.SAO_PAULO),
    SANTO_ANDRE_CITY("Santo André", State.SAO_PAULO),
    RIO_DE_JANEIRO_CITY("Rio de Janeiro", State.RIO_DE_JANEIRO),
    BELO_HORIZONTE("Belo Horizonte", State.MINAS_GERAIS),
    SALVADOR("Salvador", State.BAHIA),
    CURITIBA("Curitiba", State.PARANA),

    // Estados Unidos
    LOS_ANGELES("Los Angeles", State.CALIFORNIA),
    SAN_FRANCISCO("San Francisco", State.CALIFORNIA),
    AUSTIN("Austin", State.TEXAS),
    HOUSTON("Houston", State.TEXAS),
    MIAMI("Miami", State.FLORIDA),
    NEW_YORK_CITY("New York City", State.NEW_YORK),
    CHICAGO("Chicago", State.ILLINOIS),

    // Japão
    TOKYO_CITY("Tokyo", State.TOKYO),
    OSAKA_CITY("Osaka", State.OSAKA),
    SAPPORO("Sapporo", State.HOKKAIDO),
    KYOTO_CITY("Kyoto", State.KYOTO),

    PARIS("Paris", State.ILE_DE_FRANCE),
    LYON("Lyon", State.AUVERGNE_RHONE_ALPES),

    // Coreia do Sul
    SEOUL_CITY("Seoul", State.SEOUL),
    INCHEON("Incheon", State.GYEONGGI),
    BUSAN_CITY("Busan", State.BUSAN),

    // Rússia
    MOSCOW_CITY("Moscow", State.MOSCOW),
    ST_PETERSBURG("Saint Petersburg", State.SAINT_PETERSBURG),

    // Alemanha
    MUNICH("Munich", State.BAVARIA),
    BERLIN_CITY("Berlin", State.BERLIN),
    HAMBURG_CITY("Hamburg", State.HAMBURG),

    // China
    BEIJING_CITY("Beijing", State.BEIJING),
    SHANGHAI_CITY("Shanghai", State.SHANGHAI),
    GUANGZHOU("Guangzhou", State.GUANGDONG);

    private final String name;
    private final State state;

    // Construtor
    City(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    @JsonValue
    @Override
    public String toString() {
        return name;
    }

    // Método que recebe o nome da cidade como String e retorna o Enum correspondente
    @JsonCreator
    public static City fromString(String name) {
        for (City city : City.values()) {
            if (city.name.equalsIgnoreCase(name)) {
                return city;
            }
        }
        throw new IllegalArgumentException("Unknown city: " + name);
    }
}
