package com.mjc.school.service.impl;

import com.mjc.school.controller.request.AuthorRequest;
import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorEntity;
import com.mjc.school.repository.model.impl.NewsEntity;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.mapper.AuthorMapper;

import java.time.LocalDateTime;
import java.util.List;

public class AuthorService implements BaseService<AuthorRequest, AuthorDto, Long> {
    BaseRepository<AuthorEntity, Long> baseRepository;

    public AuthorService(BaseRepository<AuthorEntity, Long> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public List<AuthorDto> readAll() {
        return baseRepository.readAll()
                .stream().map(AuthorMapper.INSTANCE::authorEntityToAuthorDto)
                .toList();
    }

    @Override
    public AuthorDto readById(Long id) {
        return baseRepository.readById(id)
                .map(this::mapToAuthorDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public AuthorDto create(AuthorRequest createRequest) {
        AuthorDto authorDto = new AuthorDto(
                createRequest.getName(),
                LocalDateTime.now(),
                LocalDateTime.now());
        baseRepository.create(mapToAuthorEntity(authorDto));
        return authorDto;
    }

    @Override
    public AuthorDto update(AuthorRequest updateRequest) {
        if (!baseRepository.existById(updateRequest.getId())) {
            throw new RuntimeException();
        }
        AuthorDto authorDto = new AuthorDto(updateRequest.getName(),
                LocalDateTime.now(),
                LocalDateTime.now());
        AuthorEntity authorEntity = mapToAuthorEntity(authorDto);
        authorEntity.setId(updateRequest.getId());
        baseRepository.update(authorEntity);
        return authorDto;
    }

    @Override
    public boolean deleteById(Long id) {
        if (!baseRepository.existById(id)) {
            throw new RuntimeException();
        }
        return baseRepository.deleteById(id);
    }

    private AuthorDto mapToAuthorDto(AuthorEntity authorEntity) {
        return AuthorMapper.INSTANCE.authorEntityToAuthorDto(authorEntity);
    }

    private AuthorEntity mapToAuthorEntity(AuthorDto authorDto) {
        return AuthorMapper.INSTANCE.authorDtoToAuthorEntity(authorDto);
    }
}
