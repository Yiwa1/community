package com.yiwa.community.dto;

public class GitHubUploadImageDTO {
    private String message;
    private String content;
    private GitHubCommitterDTO committerDTO;

    public GitHubUploadImageDTO() {
    }

    public GitHubUploadImageDTO(String message, String content, GitHubCommitterDTO committerDTO) {
        this.message = message;
        this.content = content;
        this.committerDTO = committerDTO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GitHubCommitterDTO getCommitterDTO() {
        return committerDTO;
    }

    public void setCommitterDTO(GitHubCommitterDTO committerDTO) {
        this.committerDTO = committerDTO;
    }
}
