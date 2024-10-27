package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class NewsEntity implements BaseEntity<Long> {
    private static Long count = 1L;
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdatedDate;
    private Long authorId;

    public NewsEntity(String title, String content, LocalDateTime createDate, LocalDateTime lastUpdatedDate, Long authorId) {
        this.id = count++;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.authorId = authorId;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
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
        NewsEntity news = (NewsEntity) o;
        return Objects.equals(title, news.title) && Objects.equals(content, news.content) && Objects.equals(authorId, news.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, authorId);
    }
}
