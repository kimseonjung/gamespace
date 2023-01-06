package com.semi.gamespace.member.model.dto;

import java.sql.Date;

public class FollowDTO {
    private String followCode;
    private int followReq;
    private int followTar;
    private Date followDate;

    public FollowDTO() {
    }

    public FollowDTO(String followCode, int followReq, int followTar, Date followDate) {
        this.followCode = followCode;
        this.followReq = followReq;
        this.followTar = followTar;
        this.followDate = followDate;
    }

    public String getFollowCode() {
        return followCode;
    }
    public int getFollowReq() {
        return followReq;
    }
    public int getFollowTar() {
        return followTar;
    }
    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowCode(String followCode) {
        this.followCode = followCode;
    }
    public void setFollowReq(int followReq) {
        this.followReq = followReq;
    }
    public void setFollowTar(int followTar) {
        this.followTar = followTar;
    }
    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }
}
