package com.yiwa.community.enums;


public enum NotificationTypeEnum {
    ANSWER_QUESTION(0), //回答问题
    SUBCOMMENT(1),      //二级评论
    LIKE(2);            //点赞


    private int type;

    NotificationTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
