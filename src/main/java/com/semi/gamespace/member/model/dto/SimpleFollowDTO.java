package com.semi.gamespace.member.model.dto;

import java.sql.Date;

public class SimpleFollowDTO {
    private String memberCode;
    private String userNickname;
    private String enrollDate;
    private String followDate;

    public SimpleFollowDTO() {
    }

    public SimpleFollowDTO(String memberCode, String userNickname, String enrollDate, String followDate) {
        this.memberCode = memberCode;
        this.userNickname = userNickname;
        this.enrollDate = enrollDate;
        this.followDate = followDate;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getFollowDate() {
        return followDate;
    }

    public void setFollowDate(String followDate) {
        this.followDate = followDate;
    }
}
