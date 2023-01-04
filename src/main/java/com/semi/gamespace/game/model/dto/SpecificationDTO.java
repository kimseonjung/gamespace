package com.semi.gamespace.game.model.dto;

public class SpecificationDTO {

    private String specificationCode;
    private String minimum;
    private String recommended;

    public SpecificationDTO(){}

    public SpecificationDTO(String specificationCode, String minimum, String recommended) {
        this.specificationCode = specificationCode;
        this.minimum = minimum;
        this.recommended = recommended;
    }

    public String getSpecificationCode() {
        return specificationCode;
    }

    public void setSpecificationCode(String specificationCode) {
        this.specificationCode = specificationCode;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    @Override
    public String toString() {
        return "SpecificationDTO{" +
                "specificationCode='" + specificationCode + '\'' +
                ", minimum='" + minimum + '\'' +
                ", recommended='" + recommended + '\'' +
                '}';
    }
}
