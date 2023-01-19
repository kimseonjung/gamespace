package com.semi.gamespace.config;

import lombok.Builder;

@Builder
public class ImageConfiguration {
    private long maxFileSize;
    private String encodingType;
    private String refCode;
    private String fileType;
    private String imagePath;
    private String thumbnailPath;

    public ImageConfiguration() {}

    public ImageConfiguration(long maxFileSize, String encodingType, String refCode, String fileType, String imagePath, String thumbnailPath) {
        this.maxFileSize = maxFileSize;
        this.encodingType = encodingType;
        this.refCode = refCode;
        this.fileType = fileType;
        this.imagePath = imagePath;
        this.thumbnailPath = thumbnailPath;
    }
    public long getMaxFileSize() {
        return maxFileSize;
    }
    public String getEncodingType() {
        return encodingType;
    }
    public String getRefCode() {
        return refCode;
    }
    public String getFileType() {
        return fileType;
    }
    public String getImagePath() {
        return imagePath;
    }
    public String getThumbnailPath() {
        return thumbnailPath;
    }
}
