package com.semi.gamespace.member.model.dto;

public class MemberAuthorityDTO {
    private int authCode;
    private String authName;
    private String authDescription;

    public MemberAuthorityDTO() {
    }

    public MemberAuthorityDTO(int authCode, String authName, String authDescript) {
        this.authCode = authCode;
        this.authName = authName;
        this.authDescription = authDescript;
    }

    public int getAuthCode() {
        return authCode;
    }
    public String getAuthName() {
        return authName;
    }
    public String getAuthDescription() {
        return authDescription;
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }
    public void setAuthName(String authName) {
        this.authName = authName;
    }
    public void setAuthDescription(String authDescription) {
        this.authDescription = authDescription;
    }

    @Override
    public String toString() {
        return "MemberAuthorityDTO{" +
                "authCode=" + authCode +
                ", authName='" + authName + '\'' +
                ", authDescript='" + authDescription + '\'' +
                '}';
    }
}
