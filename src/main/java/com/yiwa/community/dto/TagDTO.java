package com.yiwa.community.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagDTO {
    private String categoryName;
    private List<String> tags;

    public TagDTO() {
    }

    public TagDTO(String categoryName, List<String> tags) {
        this.categoryName = categoryName;
        this.tags = tags;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
