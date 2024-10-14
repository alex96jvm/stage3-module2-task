package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.datasource.AuthorDataSource;
import com.mjc.school.repository.model.impl.AuthorEntity;
import java.util.List;
import java.util.Optional;

public class AuthorRepository implements BaseRepository <AuthorEntity, Long> {

AuthorDataSource authorDataSource;

public AuthorRepository(AuthorDataSource authorDataSource){
    this.authorDataSource = authorDataSource;
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
        readAll().set(readAll().indexOf(authorEntity), authorEntity);
        return authorEntity;
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
