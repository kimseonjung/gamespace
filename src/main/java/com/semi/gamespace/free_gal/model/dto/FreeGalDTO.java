package com.semi.gamespace.free_gal.model.dto;

import java.sql.Date;

public class FreeGalDTO {
    private String freeGalCode;
    private String freeGalTitle;
    private String view;
    private Date freeGalDate;
    private String freeGalContent;
    private String memberCode;
    private String freeGalNotice;

    public FreeGalDTO() {
    }

    public FreeGalDTO(String freeGalCode, String freeGalTitle, String view, Date freeGalDate, String freeGalContent, String memberCode, String freeGalNotice) {
        this.freeGalCode = freeGalCode;
        this.freeGalTitle = freeGalTitle;
        this.view = view;
        this.freeGalDate = freeGalDate;
        this.freeGalContent = freeGalContent;
        this.memberCode = memberCode;
        this.freeGalNotice = freeGalNotice;
    }

    public String getFreeGalCode() {
        return freeGalCode;
    }

    public void setFreeGalCode(String freeGalCode) {
        this.freeGalCode = freeGalCode;
    }

    public String getFreeGalTitle() {
        return freeGalTitle;
    }

    public void setFreeGalTitle(String freeGalTitle) {
        this.freeGalTitle = freeGalTitle;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Date getFreeGalDate() {
        return freeGalDate;
    }

    public void setFreeGalDate(Date freeGalDate) {
        this.freeGalDate = freeGalDate;
    }

    public String getFreeGalContent() {
        return freeGalContent;
    }

    public void setFreeGalContent(String freeGalContent) {
        this.freeGalContent = freeGalContent;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getFreeGalNotice() {
        return freeGalNotice;
    }

    public void setFreeGalNotice(String freeGalNotice) {
        this.freeGalNotice = freeGalNotice;
    }

    @Override
    public String toString() {
        return "FreeGalDTO{" +
                "freeGalCode='" + freeGalCode + '\'' +
                ", freeGalTitle='" + freeGalTitle + '\'' +
                ", view='" + view + '\'' +
                ", freeGalDate=" + freeGalDate +
                ", freeGalContent='" + freeGalContent + '\'' +
                ", memberCode='" + memberCode + '\'' +
                ", freeGalNotice='" + freeGalNotice + '\'' +
                '}';
    }
}
