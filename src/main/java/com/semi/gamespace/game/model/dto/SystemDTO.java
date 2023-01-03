package com.semi.gamespace.game.model.dto;

public class SystemDTO {

    private String systemCode;
    private String operatingSystem;
    private String processor;
    private String memory;
    private String storageSpace;
    private String graphic;

    public SystemDTO(){}

    public SystemDTO(String systemCode, String operatingSystem, String processor, String memory, String storageSpace, String graphic) {
        this.systemCode = systemCode;
        this.operatingSystem = operatingSystem;
        this.processor = processor;
        this.memory = memory;
        this.storageSpace = storageSpace;
        this.graphic = graphic;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getStorageSpace() {
        return storageSpace;
    }

    public void setStorageSpace(String storageSpace) {
        this.storageSpace = storageSpace;
    }

    public String getGraphic() {
        return graphic;
    }

    public void setGraphic(String graphic) {
        this.graphic = graphic;
    }

    @Override
    public String toString() {
        return "SystemDTO{" +
                "systemCode='" + systemCode + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", processor='" + processor + '\'' +
                ", memory='" + memory + '\'' +
                ", storageSpace='" + storageSpace + '\'' +
                ", graphic='" + graphic + '\'' +
                '}';
    }
}
