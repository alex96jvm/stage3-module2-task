package com.mjc.school.controller.impl;

import com.mjc.school.controller.processor.CommandBody;
import com.mjc.school.controller.processor.CommandHandler;
import com.mjc.school.controller.processor.CommandParam;
import com.mjc.school.service.request.AuthorRequest;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.controller.BaseController;
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
    @CommandHandler("readAll")
    public List<AuthorDto> readAll() {
        return baseService.readAll();
    }

    @Override
    @CommandHandler("readById")
    public AuthorDto readById(@CommandParam("id") Long id) {
        return baseService.readById(id);
    }

    @Override
    @CommandHandler("create")
    public AuthorDto create(@CommandBody AuthorRequest createRequest) {
        return baseService.create(createRequest);
    }

    @Override
    @CommandHandler("update")
    public AuthorDto update(@CommandBody AuthorRequest updateRequest) {
        return baseService.update(updateRequest);
    }

    @Override
    @CommandHandler("delete")
    public boolean deleteById(@CommandParam("id") Long id) {
        return baseService.deleteById(id);
    }
}
