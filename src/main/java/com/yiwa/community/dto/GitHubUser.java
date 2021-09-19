package com.yiwa.community.dto;

import org.springframework.stereotype.Component;

@Component
public class GitHubUser {
    private String name;;
    private Long id;
    private String bio;
    private String avatarUrl;

    public GitHubUser() {
    }

    public GitHubUser(String name, Long id, String bio,String avatarUrl) {
        this.name = name;
        this.id = id;
        this.bio = bio;
        this.avatarUrl=avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
