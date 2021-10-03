package com.yiwa.community.exception;

import org.springframework.stereotype.Component;

@Component
public class QuestionNotFoundException extends RuntimeException {
    private String message;

    public QuestionNotFoundException() {

    }

    public QuestionNotFoundException(String message) {
        this.message=message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
