package com.yiwa.community.exception;

public class CommentContentException extends RuntimeException{
    private String errorCode;
    private String message;

    public CommentContentException() {
    }

    public CommentContentException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
