package com.yiwa.community.dto;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class PicgoReturnDTO {
    private boolean success;
    private LinkedList<String> result;

    public PicgoReturnDTO() {
    }

    public PicgoReturnDTO(boolean success, LinkedList<String> result) {
        this.success = success;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public LinkedList<String> getResult() {
        return result;
    }

    public void setResult(LinkedList<String> result) {
        this.result = result;
    }
}
