package com.mjc.school.service.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class AuthorDto {
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdatedDate;

    public AuthorDto(String name, LocalDateTime createDate, LocalDateTime lastUpdatedDate) {
        this.name = name;
        this.createDate = createDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
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
        AuthorDto authorDto = (AuthorDto) o;
        return Objects.equals(id, authorDto.id) && Objects.equals(name, authorDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "AuthorDtoResponse[" +
                "id=" + id +
                ", name" + name +
                ", createDate=" + createDate.truncatedTo(ChronoUnit.SECONDS) +
                ", lastUpdatedDate=" + lastUpdatedDate.truncatedTo(ChronoUnit.SECONDS) +
                ']';
    }
}
