package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.datasource.AuthorDataSource;
import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.model.impl.AuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository <AuthorEntity, Long> {

private final AuthorDataSource authorDataSource;
private final NewsDataSource newsDataSource;

@Autowired
public AuthorRepository(AuthorDataSource authorDataSource, NewsDataSource newsDataSource){
    this.authorDataSource = authorDataSource;
    this.newsDataSource = newsDataSource;
}

    @Override
    public List<AuthorEntity> readAll() {
        return authorDataSource.getAuthors();
    }

    @Override
    public Optional<AuthorEntity> readById(Long id) {
        return readAll()
                .stream()
                .filter(newsEntity -> newsEntity.getId().equals(id))
                .findAny();
    }

    @Override
    public AuthorEntity create(AuthorEntity authorEntity) {
        readAll().add(authorEntity);
        return authorEntity;
    }

    @Override
    public AuthorEntity update(AuthorEntity authorEntity) {
        authorEntity.setCreateDate(readById(authorEntity.getId()).orElseThrow().getCreateDate());
        readAll().set(readAll().indexOf(readById(authorEntity.getId()).orElseThrow()), authorEntity);
        return authorEntity;
    }

    @Override
    public boolean deleteById(Long id) {
        newsDataSource.getAllNews().removeIf(newsEntity -> newsEntity.getAuthorId().equals(id));
        return readById(id).isPresent() && readAll().remove(readById(id).get());
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
