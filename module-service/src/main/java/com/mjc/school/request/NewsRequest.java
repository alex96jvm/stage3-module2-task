package com.mjc.school.request;

import java.util.Objects;

public class NewsRequest {
    private Long id;
    private String title;
    private String content;
    private Long authorId;

    public NewsRequest(String title, String content, Long authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    public NewsRequest() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsRequest newsDTO = (NewsRequest) o;
        return Objects.equals(title, newsDTO.title) && Objects.equals(content, newsDTO.content) && Objects.equals(authorId, newsDTO.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, authorId);
    }
}
