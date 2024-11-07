package br.com.jrr.apiTest.enums.adress;

import br.com.jrr.apiTest.enums.Language;

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

    @Override
    public String toString() {
        return name;
    }
}
