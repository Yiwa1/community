package com.yiwa.community.dto;

import com.yiwa.community.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class CommentDTO {
    private Long id;
    private int type;
    private int parentId;
    private String content;
    private User creator;
    private Long likeCount;
    private Long gmtCreate;
    private Long gmtModified;

    public CommentDTO() {
    }

    public CommentDTO(Long id, int type, int parentId, String content, User creator, Long likeCount, Long gmtCreate, Long gmtModified) {
        this.id = id;
        this.type = type;
        this.parentId = parentId;
        this.content = content;
        this.creator = creator;
        this.likeCount = likeCount;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
