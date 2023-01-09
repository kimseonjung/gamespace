package com.semi.gamespace.game.model.dto;

public class MinimumSystemDTO {

    private String minSystemCode;
    private String minOperatingSystem;
    private String minProcessor;
    private String minMemory;
    private String minStorageSpace;
    private String minGraphic;

    public MinimumSystemDTO() {}

    public MinimumSystemDTO(String minSystemCode, String minOperatingSystem, String minProcessor, String minMemory, String minStorageSpace, String minGraphic) {
        this.minSystemCode = minSystemCode;
        this.minOperatingSystem = minOperatingSystem;
        this.minProcessor = minProcessor;
        this.minMemory = minMemory;
        this.minStorageSpace = minStorageSpace;
        this.minGraphic = minGraphic;
    }

    public String getMinSystemCode() {
        return minSystemCode;
    }

    public void setMinSystemCode(String minSystemCode) {
        this.minSystemCode = minSystemCode;
    }

    public String getMinOperatingSystem() {
        return minOperatingSystem;
    }

    public void setMinOperatingSystem(String minOperatingSystem) {
        this.minOperatingSystem = minOperatingSystem;
    }

    public String getMinProcessor() {
        return minProcessor;
    }

    public void setMinProcessor(String minProcessor) {
        this.minProcessor = minProcessor;
    }

    public String getMinMemory() {
        return minMemory;
    }

    public void setMinMemory(String minMemory) {
        this.minMemory = minMemory;
    }

    public String getMinStorageSpace() {
        return minStorageSpace;
    }

    public void setMinStorageSpace(String minStorageSpace) {
        this.minStorageSpace = minStorageSpace;
    }

    public String getMinGraphic() {
        return minGraphic;
    }

    public void setMinGraphic(String minGraphic) {
        this.minGraphic = minGraphic;
    }

    @Override
    public String toString() {
        return "MinimumSystemDTO{" +
                "minSystemCode='" + minSystemCode + '\'' +
                ", minOperatingSystem='" + minOperatingSystem + '\'' +
                ", minProcessor='" + minProcessor + '\'' +
                ", minMemory='" + minMemory + '\'' +
                ", minStorageSpace='" + minStorageSpace + '\'' +
                ", minGraphic='" + minGraphic + '\'' +
                '}';
    }
}
