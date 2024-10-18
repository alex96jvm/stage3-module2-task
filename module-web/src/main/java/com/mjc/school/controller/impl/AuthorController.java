package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.request.AuthorRequest;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class AuthorController implements BaseController<AuthorRequest, AuthorDto, Long> {
    BaseService<AuthorRequest, AuthorDto, Long> baseService;

    @Autowired
    public AuthorController(BaseService<AuthorRequest, AuthorDto, Long> baseService) {
        this.baseService = baseService;
    }

    @Override
    public List<AuthorDto> readAll() {
        return baseService.readAll();
    }

    @Override
    public AuthorDto readById(Long id) {
        return baseService.readById(id);
    }

    @Override
    public AuthorDto create(AuthorRequest createRequest) {
        return baseService.create(createRequest);
    }

    @Override
    public AuthorDto update(AuthorRequest updateRequest) {
        return baseService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return baseService.deleteById(id);
    }
}
