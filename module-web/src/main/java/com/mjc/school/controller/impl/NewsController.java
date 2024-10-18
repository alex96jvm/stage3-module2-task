package com.mjc.school.controller.impl;

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
    public List<NewsDto> readAll() {
        return baseService.readAll();
    }

    @Override
    public NewsDto readById(Long id) {
        return baseService.readById(id);
    }

    @Override
    public NewsDto create(NewsRequest createRequest) {
        return baseService.create(createRequest);
    }

    @Override
    public NewsDto update(NewsRequest updateRequest) {
        return baseService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return baseService.deleteById(id);
    }
}
