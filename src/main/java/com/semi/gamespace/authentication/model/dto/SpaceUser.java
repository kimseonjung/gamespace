package com.semi.gamespace.authentication.model.dto;

import com.semi.gamespace.member.model.dto.MemberDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Map;

public class SpaceUser extends User implements OAuth2User, Serializable {
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
    private Date enrollDate;
    private String memberStatus;
    private Date banDate;
    private String isAdmin;

    public SpaceUser(MemberDTO member, Collection<? extends GrantedAuthority> authorities) {
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

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return null;
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
    public String getUserBirthday() {
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

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }
    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    public void setUserIntroduce(String userIntroduce) {
        this.userIntroduce = userIntroduce;
    }
    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }
    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }
    public void setBanDate(Date banDate) {
        this.banDate = banDate;
    }
    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }
}
