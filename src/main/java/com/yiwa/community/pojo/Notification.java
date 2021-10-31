package com.yiwa.community.pojo;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("Notification")
public class Notification {
    private Long id;
    private String notifier;
    private String receiver;
    private String content;
    private int relateId;
    private int type;
    private Long gmtCreate;
    private int status;

    public Notification() {
    }

    public Notification(Long id, String notifier, String receiver, String content, int relateId, int type, Long gmtCreate, int status) {
        this.id = id;
        this.notifier = notifier;
        this.receiver = receiver;
        this.content = content;
        this.relateId = relateId;
        this.type = type;
        this.gmtCreate = gmtCreate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotifier() {
        return notifier;
    }

    public void setNotifier(String notifier) {
        this.notifier = notifier;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRelateId() {
        return relateId;
    }

    public void setRelateId(int relateId) {
        this.relateId = relateId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
