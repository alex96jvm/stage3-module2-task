package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class AuthorEntity implements BaseEntity<Long> {
    private static Long count = 1L;
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdatedDate;

    public AuthorEntity(String name, LocalDateTime createDate, LocalDateTime lastUpdatedDate) {
        this.id = count++;
        this.name = name;
        this.createDate = createDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
