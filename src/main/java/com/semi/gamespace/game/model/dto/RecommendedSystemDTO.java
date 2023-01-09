package com.semi.gamespace.game.model.dto;

public class RecommendedSystemDTO {

    private String recSystemCode;
    private String recOperatingSystem;
    private String recProcessor;
    private String recMemory;
    private String recStorageSpace;
    private String recGraphic;

    public RecommendedSystemDTO() {}

    public RecommendedSystemDTO(String recSystemCode, String recOperatingSystem, String recProcessor, String recMemory, String recStorageSpace, String recGraphic) {
        this.recSystemCode = recSystemCode;
        this.recOperatingSystem = recOperatingSystem;
        this.recProcessor = recProcessor;
        this.recMemory = recMemory;
        this.recStorageSpace = recStorageSpace;
        this.recGraphic = recGraphic;
    }

    public String getRecSystemCode() {
        return recSystemCode;
    }

    public void setRecSystemCode(String recSystemCode) {
        this.recSystemCode = recSystemCode;
    }

    public String getRecOperatingSystem() {
        return recOperatingSystem;
    }

    public void setRecOperatingSystem(String recOperatingSystem) {
        this.recOperatingSystem = recOperatingSystem;
    }

    public String getRecProcessor() {
        return recProcessor;
    }

    public void setRecProcessor(String recProcessor) {
        this.recProcessor = recProcessor;
    }

    public String getRecMemory() {
        return recMemory;
    }

    public void setRecMemory(String recMemory) {
        this.recMemory = recMemory;
    }

    public String getRecStorageSpace() {
        return recStorageSpace;
    }

    public void setRecStorageSpace(String recStorageSpace) {
        this.recStorageSpace = recStorageSpace;
    }

    public String getRecGraphic() {
        return recGraphic;
    }

    public void setRecGraphic(String recGraphic) {
        this.recGraphic = recGraphic;
    }

    @Override
    public String toString() {
        return "RecommendedSystemDTO{" +
                "recSystemCode='" + recSystemCode + '\'' +
                ", recOperatingSystem='" + recOperatingSystem + '\'' +
                ", recProcessor='" + recProcessor + '\'' +
                ", recMemory='" + recMemory + '\'' +
                ", recStorageSpace='" + recStorageSpace + '\'' +
                ", recGraphic='" + recGraphic + '\'' +
                '}';
    }
}
