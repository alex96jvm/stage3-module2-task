package com.mjc.school.service.impl;

import com.mjc.school.controller.request.AuthorRequest;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDto;
import java.util.List;

public class AuthorService implements BaseService<AuthorRequest, AuthorDto, Long> {
    @Override
    public List<AuthorDto> readAll() {
        return List.of();
    }

    @Override
    public AuthorDto readById(Long id) {
        return null;
    }

    @Override
    public AuthorDto create(AuthorRequest createRequest) {
        return null;
    }

    @Override
    public AuthorDto update(AuthorRequest updateRequest) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
