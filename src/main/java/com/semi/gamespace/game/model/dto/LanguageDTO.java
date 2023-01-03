package com.semi.gamespace.game.model.dto;

public class LanguageDTO {

    private String languageCode;
    private String languageName;

    public LanguageDTO(){}

    public LanguageDTO(String languageCode, String languageName) {
        this.languageCode = languageCode;
        this.languageName = languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public String toString() {
        return "LanguageDTO{" +
                "languageCode='" + languageCode + '\'' +
                ", languageName='" + languageName + '\'' +
                '}';
    }
}
