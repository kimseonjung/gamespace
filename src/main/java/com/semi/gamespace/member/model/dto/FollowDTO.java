package com.semi.gamespace.member.model.dto;

import java.sql.Date;

public class FollowDTO {
    private String followCode;
    private String followReq;
    private String followTar;
    private Date followDate;

    public FollowDTO() {
    }

    public FollowDTO(String followCode, String followReq, String followTar, Date followDate) {
        this.followCode = followCode;
        this.followReq = followReq;
        this.followTar = followTar;
        this.followDate = followDate;
    }

    public String getFollowCode() {
        return followCode;
    }
    public String getFollowReq() {
        return followReq;
    }
    public String getFollowTar() {
        return followTar;
    }
    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowCode(String followCode) {
        this.followCode = followCode;
    }
    public void setFollowReq(String followReq) {
        this.followReq = followReq;
    }
    public void setFollowTar(String followTar) {
        this.followTar = followTar;
    }
    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }
}
