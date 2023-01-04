package com.semi.gamespace.game.model.dto;

public class AttFileDTO {

    private String attachedNo;
    private String refCode;
    private String originalName;
    private String savedName;
    private String savePath;
    private String fileType;
    private String thumbnailPath;
    private String attachedStatus;

    public AttFileDTO(){}

    public AttFileDTO(String attachedNo, String refCode, String originalName, String savedName, String savePath, String fileType, String thumbnailPath, String attachedStatus) {
        this.attachedNo = attachedNo;
        this.refCode = refCode;
        this.originalName = originalName;
        this.savedName = savedName;
        this.savePath = savePath;
        this.fileType = fileType;
        this.thumbnailPath = thumbnailPath;
        this.attachedStatus = attachedStatus;
    }

    public String getAttachedNo() {
        return attachedNo;
    }

    public void setAttachedNo(String attachedNo) {
        this.attachedNo = attachedNo;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getSavedName() {
        return savedName;
    }

    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getAttachedStatus() {
        return attachedStatus;
    }

    public void setAttachedStatus(String attachedStatus) {
        this.attachedStatus = attachedStatus;
    }

    @Override
    public String toString() {
        return "AttFileDTO{" +
                "attachedNo='" + attachedNo + '\'' +
                ", refCode='" + refCode + '\'' +
                ", originalName='" + originalName + '\'' +
                ", savedName='" + savedName + '\'' +
                ", savePath='" + savePath + '\'' +
                ", fileType='" + fileType + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", attachedStatus='" + attachedStatus + '\'' +
                '}';
    }
}
