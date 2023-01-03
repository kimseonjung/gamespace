package com.semi.gamespace.game.model.dto;

import java.util.Date;

public class GameInfoDTO {

    private String gameCode;
    private String gameName;
    private String devicesCode;
    private String categoryCode;
    private String tagCode;
    private Date registrationDate;
    private String launchDate;
    private String developer;
    private String distributorCode;
    private String ratingCode;
    private String price;
    private String platformCode;
    private String languageCode;
    private String specificationCode;
    private String gameIntro;
    private String gameStatus;

    public GameInfoDTO(){}

    public GameInfoDTO(String gameCode, String gameName, String devicesCode, String categoryCode,
                       String tagCode, Date registrationDate, String launchDate, String developer,
                       String distributorCode, String ratingCode, String price, String platformCode,
                       String languageCode, String specificationCode, String gameIntro, String gameStatus) {
        this.gameCode = gameCode;
        this.gameName = gameName;
        this.devicesCode = devicesCode;
        this.categoryCode = categoryCode;
        this.tagCode = tagCode;
        this.registrationDate = registrationDate;
        this.launchDate = launchDate;
        this.developer = developer;
        this.distributorCode = distributorCode;
        this.ratingCode = ratingCode;
        this.price = price;
        this.platformCode = platformCode;
        this.languageCode = languageCode;
        this.specificationCode = specificationCode;
        this.gameIntro = gameIntro;
        this.gameStatus = gameStatus;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDevicesCode() {
        return devicesCode;
    }

    public void setDevicesCode(String devicesCode) {
        this.devicesCode = devicesCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getDistributorCode() {
        return distributorCode;
    }

    public void setDistributorCode(String distributorCode) {
        this.distributorCode = distributorCode;
    }

    public String getRatingCode() {
        return ratingCode;
    }

    public void setRatingCode(String ratingCode) {
        this.ratingCode = ratingCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getSpecificationCode() {
        return specificationCode;
    }

    public void setSpecificationCode(String specificationCode) {
        this.specificationCode = specificationCode;
    }

    public String getGameIntro() {
        return gameIntro;
    }

    public void setGameIntro(String gameIntro) {
        this.gameIntro = gameIntro;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public String toString() {
        return "GameInfoDTO{" +
                "gameCode='" + gameCode + '\'' +
                ", gameName='" + gameName + '\'' +
                ", devicesCode='" + devicesCode + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", tagCode='" + tagCode + '\'' +
                ", registrationDate=" + registrationDate +
                ", launchDate='" + launchDate + '\'' +
                ", developer='" + developer + '\'' +
                ", distributorCode='" + distributorCode + '\'' +
                ", ratingCode='" + ratingCode + '\'' +
                ", price='" + price + '\'' +
                ", platformCode='" + platformCode + '\'' +
                ", languageCode='" + languageCode + '\'' +
                ", specificationCode='" + specificationCode + '\'' +
                ", gameIntro='" + gameIntro + '\'' +
                ", gameStatus='" + gameStatus + '\'' +
                '}';
    }
}
