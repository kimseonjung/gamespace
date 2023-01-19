package com.semi.gamespace.game.model.dto;

public class SpecificationDTO {

    private String specificationCode;
    private String minSystemCode;
    private String recSystemCode;

    public SpecificationDTO() {}


    public SpecificationDTO(String specificationCode, String minSystemCode, String recSystemCode) {
        this.specificationCode = specificationCode;
        this.minSystemCode = minSystemCode;
        this.recSystemCode = recSystemCode;
    }

    public String getSpecificationCode() {
        return specificationCode;
    }

    public void setSpecificationCode(String specificationCode) {
        this.specificationCode = specificationCode;
    }

    public String getMinSystemCode() {
        return minSystemCode;
    }

    public void setMinSystemCode(String minSystemCode) {
        this.minSystemCode = minSystemCode;
    }

    public String getRecSystemCode() {
        return recSystemCode;
    }

    public void setRecSystemCode(String recSystemCode) {
        this.recSystemCode = recSystemCode;
    }

    @Override
    public String toString() {
        return "SpecificationDTO{" +
                "specificationCode='" + specificationCode + '\'' +
                ", minSystemCode='" + minSystemCode + '\'' +
                ", recSystemCode='" + recSystemCode + '\'' +
                '}';
    }
}
