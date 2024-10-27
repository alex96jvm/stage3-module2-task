package com.mjc.school.controller.impl;

import com.mjc.school.controller.annotation.CommandBody;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.annotation.CommandParam;
import com.mjc.school.service.request.NewsRequest;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class NewsController implements BaseService<NewsRequest, NewsDto, Long> {
    BaseService<NewsRequest, NewsDto, Long> baseService;

    @Autowired
    public NewsController(BaseService<NewsRequest, NewsDto, Long> baseService){
        this.baseService = baseService;
    }

    @Override
    @CommandHandler("readAll")
    public List<NewsDto> readAll() {
        return baseService.readAll();
    }

    @Override
    @CommandHandler("readById")
    public NewsDto readById(@CommandParam("id") Long id) {
        return baseService.readById(id);
    }

    @Override
    @CommandHandler("create")
    public NewsDto create(@CommandBody NewsRequest createRequest) {
        return baseService.create(createRequest);
    }

    @Override
    @CommandHandler("update")
    public NewsDto update(@CommandBody NewsRequest updateRequest) {
        return baseService.update(updateRequest);
    }

    @Override
    @CommandHandler("delete")
    public boolean deleteById(@CommandParam("id") Long id) {
        return baseService.deleteById(id);
    }
}
