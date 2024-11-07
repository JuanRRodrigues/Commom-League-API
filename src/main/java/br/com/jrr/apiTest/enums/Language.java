package br.com.jrr.apiTest.enums;

public enum Language {
    PORTUGUESE(1, "pt", "Portuguese-BR"),
    ENGLISH(2, "eng", "English"),
    JAPANESE(3, "jpn", "日本語"),
    FRENCH(4, "fr", "Français"),
    KOREAN(5, "ko", "한국어"),
    RUSSIAN(6, "ru", "Русский"),
    GERMAN(7, "de", "Deutsch"),
    CHINESE(8, "zh", "中文");

    private final int id;
    private final String code;
    private final String displayName;

    Language(int id, String code, String displayName) {
        this.id = id;
        this.code = code;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
