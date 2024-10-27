package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.model.impl.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsEntity, Long> {
    private final NewsDataSource newsDataSource;

    @Autowired
    public NewsRepository(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public List<NewsEntity> readAll() {
        return newsDataSource.getAllNews();
    }

    @Override
    public Optional<NewsEntity> readById(Long id) {
        return readAll()
                .stream()
                .filter(newsEntity -> newsEntity.getId().equals(id))
                .findAny();
    }

    @Override
    public NewsEntity create(NewsEntity newsEntity) {
        readAll().add(newsEntity);
        return newsEntity;
    }

    @Override
    public NewsEntity update(NewsEntity newsEntity) {
        newsEntity.setCreateDate(readById(newsEntity.getId()).orElseThrow().getCreateDate());
        readAll().set(readAll().indexOf(readById(newsEntity.getId()).orElseThrow()), newsEntity);
        return newsEntity;
    }

    @Override
    public boolean deleteById(Long id) {
        return readById(id).isPresent() && readAll().remove(readById(id).get());
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
