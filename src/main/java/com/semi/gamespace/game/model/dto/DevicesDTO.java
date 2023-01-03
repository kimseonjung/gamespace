package com.semi.gamespace.game.model.dto;

public class DevicesDTO {

    private String devicesCode;
    private String devicesName;

    public DevicesDTO(){}

    public DevicesDTO(String devicesCode, String devicesName) {
        this.devicesCode = devicesCode;
        this.devicesName = devicesName;
    }

    public String getDevicesCode() {
        return devicesCode;
    }

    public void setDevicesCode(String devicesCode) {
        this.devicesCode = devicesCode;
    }

    public String getDevicesName() {
        return devicesName;
    }

    public void setDevicesName(String devicesName) {
        this.devicesName = devicesName;
    }

    @Override
    public String toString() {
        return "DevicesDTO{" +
                "devicesCode='" + devicesCode + '\'' +
                ", devicesName='" + devicesName + '\'' +
                '}';
    }
}
