package br.com.jrr.apiTest.enums.adress;

import br.com.jrr.apiTest.enums.Language;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Country {
    BRAZIL("Brazil", Language.PORTUGUESE),
    UNITED_STATES("United States", Language.ENGLISH),
    JAPAN("Japan", Language.JAPANESE),
    FRANCE("France", Language.FRENCH),
    SOUTH_KOREA("South Korea", Language.KOREAN),
    RUSSIA("Russia", Language.RUSSIAN),
    GERMANY("Germany", Language.GERMAN),
    CHINA("China", Language.CHINESE);

    private final String name;
    private final Language language;

    Country(String name, Language language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public Language getLanguage() {
        return language;
    }

    @JsonValue
    public String getValue() {
        return name;
    }

    @JsonCreator
    public static Country fromValue(String value) {
        for (Country country : values()) {
            if (country.getValue().equalsIgnoreCase(value)) {
                return country;
            }
        }
        throw new IllegalArgumentException("Invalid country: " + value);
    }

    @Override
    public String toString() {
        return name;
    }
}
