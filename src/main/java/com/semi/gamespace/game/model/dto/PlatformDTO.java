package com.semi.gamespace.game.model.dto;

public class PlatformDTO {

    private String platformCode;
    private String platformName;

    public PlatformDTO(){}

    public PlatformDTO(String platformCode, String platformName) {
        this.platformCode = platformCode;
        this.platformName = platformName;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    @Override
    public String toString() {
        return "PlatformDTO{" +
                "platformCode='" + platformCode + '\'' +
                ", platformName='" + platformName + '\'' +
                '}';
    }
}
