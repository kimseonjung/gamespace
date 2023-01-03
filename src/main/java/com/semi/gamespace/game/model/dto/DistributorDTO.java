package com.semi.gamespace.game.model.dto;

public class DistributorDTO {

    private String distributorCode;
    private String distributorName;

    public  DistributorDTO(){}

    public DistributorDTO(String distributorCode, String distributorName) {
        this.distributorCode = distributorCode;
        this.distributorName = distributorName;
    }

    public String getDistributorCode() {
        return distributorCode;
    }

    public void setDistributorCode(String distributorCode) {
        this.distributorCode = distributorCode;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    @Override
    public String toString() {
        return "DistributorDTO{" +
                "distributorCode='" + distributorCode + '\'' +
                ", distributorName='" + distributorName + '\'' +
                '}';
    }
}
