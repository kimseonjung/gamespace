package com.semi.gamespace.authentication.model.dto;

import com.semi.gamespace.member.model.dto.MemberDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.sql.Date;
import java.util.Collection;

public class spaceUser extends User {
    private String memberCode;
    private String userId;
    private String userPwd;
    private String userNickname;
    private String userName;
    private String userPhone;
    private String userEmail;
    private Date userBirthday;
    private String userGender;
    private String userAddress;
    private String userIntroduce;
    private Date enrollDate;
    private String memberStatus;
    private Date banDate;
    private String isAdmin;

    public spaceUser(MemberDTO member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getUserId(), member.getUserPwd(), authorities);
        setMemberDetail(member);
    }

    private void setMemberDetail(MemberDTO member) {
        this.memberCode = member.getMemberCode();
        this.userId = member.getUserId();
        this.userPwd = member.getUserPwd();
        this.userNickname = member.getUserNickname();
        this.userName = member.getUserName();
        this.userPhone = member.getUserPhone();
        this.userEmail = member.getUserEmail();
        this.userBirthday = member.getUserBirthday();
        this.userGender = member.getUserGender();
        this.userAddress = member.getUserAddress();
        this.userIntroduce = member.getUserIntroduce();
        this.enrollDate = member.getEnrollDate();
        this.memberStatus = member.getMemberStatus();
        this.banDate = member.getBanDate();
        this.isAdmin = member.getIsAdmin();
    }

    public String getMemberCode() {
        return memberCode;
    }
    public String getUserId() {
        return userId;
    }
    public String getUserPwd() {
        return userPwd;
    }
    public String getUserNickname() {
        return userNickname;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserPhone() {
        return userPhone;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public Date getUserBirthday() {
        return userBirthday;
    }
    public String getUserGender() {
        return userGender;
    }
    public String getUserAddress() {
        return userAddress;
    }
    public String getUserIntroduce() {
        return userIntroduce;
    }
    public Date getEnrollDate() {
        return enrollDate;
    }
    public String getMemberStatus() {
        return memberStatus;
    }
    public Date getBanDate() {
        return banDate;
    }
    public String getIsAdmin() {
        return isAdmin;
    }


}