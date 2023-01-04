package com.semi.gamespace.game.model.dto;

public class RatingDTO {

    private String ratingCode;
    private String ratingName;

    public RatingDTO(){}

    public RatingDTO(String ratingCode, String ratingName) {
        this.ratingCode = ratingCode;
        this.ratingName = ratingName;
    }

    public String getRatingCode() {
        return ratingCode;
    }

    public void setRatingCode(String ratingCode) {
        this.ratingCode = ratingCode;
    }

    public String getRatingName() {
        return ratingName;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    @Override
    public String toString() {
        return "RatingDTO{" +
                "ratingCode='" + ratingCode + '\'' +
                ", ratingName='" + ratingName + '\'' +
                '}';
    }
}
