package com.semi.gamespace.news.model.dto;

import java.sql.Date;

public class NewsDTO {

    private String newsCode;
    private String newsTitle;
    private String newsView;
    private Date newsDate;
    private String newsContent;
    private String gameName;
    private String memberName;

    public NewsDTO() {
    }

    public NewsDTO(String newsCode, String newsTitle, String newsView, Date newsDate, String newsContent, String gameName, String memberName) {
        this.newsCode = newsCode;
        this.newsTitle = newsTitle;
        this.newsView = newsView;
        this.newsDate = newsDate;
        this.newsContent = newsContent;
        this.gameName = gameName;
        this.memberName = memberName;
    }

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsView() {
        return newsView;
    }

    public void setNewsView(String newsView) {
        this.newsView = newsView;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "NewsDTO{" +
                "newsCode='" + newsCode + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsView='" + newsView + '\'' +
                ", newsDate=" + newsDate +
                ", newsContent='" + newsContent + '\'' +
                ", gameName='" + gameName + '\'' +
                ", memberName='" + memberName + '\'' +
                '}';
    }
}
