package com.yiwa.community.dto;

import org.springframework.stereotype.Component;

@Component
public class ImageFileDTO {
    private Integer success;
    private String message;
    private String url;

    public ImageFileDTO() {
    }

    public ImageFileDTO(Integer success, String message, String url) {
        this.success = success;
        this.message = message;
        this.url = url;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
