package com.semi.gamespace.news.model.dto;

import java.sql.Date;

public class NewsComDTO {
    private String newsComCode;
    private String newsCode;
    private String newsCom;
    private String memberCode;
    private Date regDate;

    public NewsComDTO() {
    }

    public NewsComDTO(String newsComCode, String newsCode, String newsCom, String memberCode, Date regDate) {
        this.newsComCode = newsComCode;
        this.newsCode = newsCode;
        this.newsCom = newsCom;
        this.memberCode = memberCode;
        this.regDate = regDate;
    }

    public String getNewsComCode() {
        return newsComCode;
    }

    public void setNewsComCode(String newsComCode) {
        this.newsComCode = newsComCode;
    }

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }

    public String getNewsCom() {
        return newsCom;
    }

    public void setNewsCom(String newsCom) {
        this.newsCom = newsCom;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "NewsComDTO{" +
                "newsComCode='" + newsComCode + '\'' +
                ", newsCode='" + newsCode + '\'' +
                ", newsCom='" + newsCom + '\'' +
                ", memberCode='" + memberCode + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
