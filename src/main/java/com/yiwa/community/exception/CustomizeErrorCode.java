package com.yiwa.community.exception;


public enum CustomizeErrorCode {

    USER_NO_LOGIN("1314","未登录，请先登录"),
    SERVER_ERROR("500","服务器太热了,正在洗冷水澡"),
    PAGE_NOT_FOUND("404","页面飞走了,试试别的吧"),
    QUESTION_NOT_FOUND("404","问题飞走了,试试别的吧"),
    COMMENT_HAVE_NO_TARGET("404","没有选择一个问题评论"),
    COMMENT_TYPE_ERROR("400","评论发布位置出错"),
    COMMENT_CONTENT_ERROR("400","评论内容异常");


    private String errorCode;
    private String message;

    CustomizeErrorCode(String errorCode, String message) {
        this.errorCode=errorCode;
        this.message=message;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}
