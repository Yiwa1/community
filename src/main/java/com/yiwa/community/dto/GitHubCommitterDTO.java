package com.yiwa.community.dto;

import org.springframework.stereotype.Component;

@Component
public class GitHubCommitterDTO {
    private String name;
    private String email;

    public GitHubCommitterDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public GitHubCommitterDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
