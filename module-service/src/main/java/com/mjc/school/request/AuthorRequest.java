package com.mjc.school.request;

import java.util.Objects;

public class AuthorRequest {
    private Long id;
    private String name;

    public AuthorRequest(String name) {
        this.name = name;
    }

    public AuthorRequest() {};

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorRequest authorDto = (AuthorRequest) o;
        return Objects.equals(id, authorDto.id) && Objects.equals(name, authorDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
