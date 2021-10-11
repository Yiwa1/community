package com.yiwa.community.pojo;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("Comment")
public class Comment{
    private Long id;
    private int type;
    private int parentId;
    private String content;
    private String creator;
    private Long likeCount;
    private Long gmtCreate;
    private Long gmtModified;


    public Comment() {
    }

    public Comment(Long id, int type, int parentId, String content, String creator, Long likeCount, Long gmtCreate, Long gmtModified) {
        this.id = id;
        this.type = type;
        this.parentId = parentId;
        this.content = content;
        this.creator = creator;
        this.likeCount=likeCount;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", type=" + type +
                ", parentId=" + parentId +
                ", content='" + content + '\'' +
                ", creator='" + creator + '\'' +
                ", likeCount=" + likeCount +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
