package com.yiwa.community.dto;

import com.yiwa.community.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class NotificationDTO {
    private Long id;
    private User notifier;
    private String content;
    private Long gmtCreate;
    private int status;
    private String description;
    private int relateId;


    public NotificationDTO() {
    }

    public NotificationDTO(Long id, User notifier, String content, Long gmtCreate,int status,String description,int relateId) {
        this.id = id;
        this.notifier = notifier;
        this.content = content;
        this.gmtCreate = gmtCreate;
        this.status = status;
        this.description=description;
        this.relateId=relateId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRelateId() {
        return relateId;
    }

    public void setRelateId(int relateId) {
        this.relateId = relateId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getNotifier() {
        return notifier;
    }

    public void setNotifier(User notifier) {
        this.notifier = notifier;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
