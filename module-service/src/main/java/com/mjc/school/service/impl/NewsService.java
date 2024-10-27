package com.mjc.school.service.impl;

import com.mjc.school.request.NewsRequest;
import com.mjc.school.repository.BaseRepository;
import com.mjc.school.model.impl.NewsEntity;
import com.mjc.school.service.BaseService;
import com.mjc.school.dto.NewsDto;
import com.mjc.school.mapper.NewsMapper;
import com.mjc.school.validation.ValidateId;
import com.mjc.school.validation.ValidateLength;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService implements BaseService<NewsRequest, NewsDto, Long> {
    private final String CHECK_NEWS_ID = "CHECK_NEWS_ID";
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
    @ValidateId(type = CHECK_NEWS_ID)
    public NewsDto readById(Long id) {
        return baseRepository.readById(id)
                .map(this::mapToNewsDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    @ValidateLength
    @ValidateId
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
    @ValidateLength
    @ValidateId
    public NewsDto update(NewsRequest updateRequest) {
        NewsEntity newsEntity = baseRepository.readById(updateRequest.getId()).orElseThrow();
        newsEntity.setTitle(updateRequest.getTitle());
        newsEntity.setContent(updateRequest.getContent());
        newsEntity.setLastUpdatedDate(LocalDateTime.now());
        newsEntity.setAuthorId(updateRequest.getAuthorId());
        return mapToNewsDto(baseRepository.update(newsEntity));
    }

    @Override
    @ValidateId(type = CHECK_NEWS_ID)
    public boolean deleteById(Long id) {
        return baseRepository.deleteById(id);
    }

    private NewsDto mapToNewsDto(NewsEntity newsEntity) {
        return NewsMapper.INSTANCE.newsEntityToNewsDto(newsEntity);
    }

    private NewsEntity mapToNewsEntity(NewsDto newsDto) {
        return NewsMapper.INSTANCE.newsDtoToNewsEntity(newsDto);
    }
}
