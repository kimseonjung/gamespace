package com.semi.gamespace.admin.model.dto;

import java.sql.Date;

public class SimpleMemberDTO {
    private String memberCode;
    private String memberId;
    private String userNickname;
    private Date enrollDate;
    private String memberStatus;
    private Date banDate;

    public SimpleMemberDTO() {
    }

    public SimpleMemberDTO(String memberCode, String memberId, String userNickname, Date enrollDate, String memberStatus, Date banDate) {
        this.memberCode = memberCode;
        this.memberId = memberId;
        this.userNickname = userNickname;
        this.enrollDate = enrollDate;
        this.memberStatus = memberStatus;
        this.banDate = banDate;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Date getBanDate() {
        return banDate;
    }

    public void setBanDate(Date banDate) {
        this.banDate = banDate;
    }
}
