package com.semi.gamespace.common.model.dto;

public class ImageDTO {
    private String attachCode;
    private String refCode;     //어떤 코드와 연관이 있는지
    private String originName;
    private String saveName;
    private String savePath;
    private String fileType;    //테이블 명으로 통일하자
    private String thumbnailPath;
    private String attachStatus;

    public ImageDTO() {}

    public ImageDTO(ImageDTO image) {
        this.attachCode = image.attachCode;
        this.refCode = image.refCode;
        this.originName = image.originName;
        this.saveName = image.saveName;
        this.savePath = image.savePath;
        this.fileType = image.fileType;
        this.thumbnailPath = image.thumbnailPath;
        this.attachStatus = image.attachStatus;
    }

    /**
     * test
     * @param attachCode
     * @return 결과값
     */
    public ImageDTO setAttachCode(String attachCode) {
        this.attachCode = attachCode;
        return this;
    }
    public ImageDTO setRefCode(String refCode) {
        this.refCode = refCode;
        return this;
    }
    public ImageDTO setOriginName(String originName) {
        this.originName = originName;
        return this;
    }
    public ImageDTO setSaveName(String saveName) {
        this.saveName = saveName;
        return this;
    }
    public ImageDTO setSavePath(String savePath) {
        this.savePath = savePath;
        return this;
    }
    public ImageDTO setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }
    public ImageDTO setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
        return this;
    }
    public ImageDTO setAttachStatus(String attachStatus) {
        this.attachStatus = attachStatus;
        return this;
    }

    public String getAttachCode() {
        return attachCode;
    }
    public String getRefCode() {
        return refCode;
    }
    public String getOriginName() {
        return originName;
    }
    public String getSaveName() {
        return saveName;
    }
    public String getSavePath() {
        return savePath;
    }
    public String getFileType() {
        return fileType;
    }
    public String getThumbnailPath() {
        return thumbnailPath;
    }
    public String getAttachStatus() {
        return attachStatus;
    }
}
