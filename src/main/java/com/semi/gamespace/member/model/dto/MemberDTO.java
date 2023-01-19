package com.semi.gamespace.member.model.dto;

import java.sql.Date;
import java.util.List;

public class MemberDTO {
    private String memberCode;
    private String userId;
    private String userPwd;
    private String userNickname;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String userBirthday;
    private String userGender;
    private String userAddress;
    private String userIntroduce;
    private String userSiteLink1;
    private String userSiteLink2;
    private String userSiteLink3;
    private String userSiteLink4;
    private String userSiteLink5;
    private String userSiteLink6;
    private Date enrollDate;
    private String memberStatus;
    private Date banDate;
    private String isAdmin;

    public MemberDTO() {}

    public MemberDTO(String memberCode, String userId, String userPwd, String userNickname, String userName,
                     String userPhone, String userEmail, String userBirthday, String userGender, String userAddress,
                     String userIntroduce, String userSiteLink1, String userSiteLink2, String userSiteLink3,
                     String userSiteLink4, String userSiteLink5, String userSiteLink6, Date enrollDate,
                     String memberStatus, Date banDate, String isAdmin) {
        this.memberCode = memberCode;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userNickname = userNickname;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userBirthday = userBirthday;
        this.userGender = userGender;
        this.userAddress = userAddress;
        this.userIntroduce = userIntroduce;
        this.userSiteLink1 = userSiteLink1;
        this.userSiteLink2 = userSiteLink2;
        this.userSiteLink3 = userSiteLink3;
        this.userSiteLink4 = userSiteLink4;
        this.userSiteLink5 = userSiteLink5;
        this.userSiteLink6 = userSiteLink6;
        this.enrollDate = enrollDate;
        this.memberStatus = memberStatus;
        this.banDate = banDate;
        this.isAdmin = isAdmin;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserIntroduce() {
        return userIntroduce;
    }

    public void setUserIntroduce(String userIntroduce) {
        this.userIntroduce = userIntroduce;
    }

    public String getUserSiteLink1() {
        return userSiteLink1;
    }

    public void setUserSiteLink1(String userSiteLink1) {
        this.userSiteLink1 = userSiteLink1;
    }

    public String getUserSiteLink2() {
        return userSiteLink2;
    }

    public void setUserSiteLink2(String userSiteLink2) {
        this.userSiteLink2 = userSiteLink2;
    }

    public String getUserSiteLink3() {
        return userSiteLink3;
    }

    public void setUserSiteLink3(String userSiteLink3) {
        this.userSiteLink3 = userSiteLink3;
    }

    public String getUserSiteLink4() {
        return userSiteLink4;
    }

    public void setUserSiteLink4(String userSiteLink4) {
        this.userSiteLink4 = userSiteLink4;
    }

    public String getUserSiteLink5() {
        return userSiteLink5;
    }

    public void setUserSiteLink5(String userSiteLink5) {
        this.userSiteLink5 = userSiteLink5;
    }

    public String getUserSiteLink6() {
        return userSiteLink6;
    }

    public void setUserSiteLink6(String userSiteLink6) {
        this.userSiteLink6 = userSiteLink6;
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

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "memberCode='" + memberCode + '\'' +
                ", userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userIntroduce='" + userIntroduce + '\'' +
                ", userSiteLink1='" + userSiteLink1 + '\'' +
                ", userSiteLink2='" + userSiteLink2 + '\'' +
                ", userSiteLink3='" + userSiteLink3 + '\'' +
                ", userSiteLink4='" + userSiteLink4 + '\'' +
                ", userSiteLink5='" + userSiteLink5 + '\'' +
                ", userSiteLink6='" + userSiteLink6 + '\'' +
                ", enrollDate=" + enrollDate +
                ", memberStatus='" + memberStatus + '\'' +
                ", banDate=" + banDate +
                ", isAdmin='" + isAdmin + '\'' +
                '}';
    }
}
