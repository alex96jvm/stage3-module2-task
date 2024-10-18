package com.mjc.school.service.impl;

import com.mjc.school.service.request.NewsRequest;
import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsEntity;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService implements BaseService<NewsRequest, NewsDto, Long> {
    private final BaseRepository<NewsEntity, Long> baseRepository;

    @Autowired
    public NewsService(BaseRepository<NewsEntity, Long> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public List<NewsDto> readAll() {
        return baseRepository.readAll()
                .stream().map(NewsMapper.INSTANCE::newsEntityToNewsDto)
                .toList();
    }

    @Override
    public NewsDto readById(Long id) {
        return baseRepository.readById(id)
                .map(this::mapToNewsDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public NewsDto create(NewsRequest createRequest) {
        NewsDto newsDto = new NewsDto(createRequest.getTitle(),
                createRequest.getContent(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                createRequest.getAuthorId());
        newsDto.setId(baseRepository.create(mapToNewsEntity(newsDto)).getId());
        return newsDto;
    }

    @Override
    public NewsDto update(NewsRequest updateRequest) {
        if (!baseRepository.existById(updateRequest.getId())) {
            throw new RuntimeException();
        }
        NewsDto newsDto = new NewsDto(updateRequest.getTitle(),
                updateRequest.getContent(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                updateRequest.getAuthorId());
        NewsEntity newsEntity = mapToNewsEntity(newsDto);
        newsEntity.setId(updateRequest.getId());
        return mapToNewsDto(baseRepository.update(newsEntity));
    }

    @Override
    public boolean deleteById(Long id) {
        if (!baseRepository.existById(id)) {
            throw new RuntimeException();
        }
        return baseRepository.deleteById(id);
    }

    private NewsDto mapToNewsDto(NewsEntity newsEntity) {
        return NewsMapper.INSTANCE.newsEntityToNewsDto(newsEntity);
    }

    private NewsEntity mapToNewsEntity(NewsDto newsDto) {
        return NewsMapper.INSTANCE.newsDtoToNewsEntity(newsDto);
    }
}
