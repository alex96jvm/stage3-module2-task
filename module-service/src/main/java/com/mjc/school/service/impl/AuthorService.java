package com.mjc.school.service.impl;

import com.mjc.school.service.request.AuthorRequest;
import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorEntity;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthorService implements BaseService<AuthorRequest, AuthorDto, Long> {
    private final BaseRepository<AuthorEntity, Long> baseRepository;

    @Autowired
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
        authorDto.setId(baseRepository.create(mapToAuthorEntity(authorDto)).getId());
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
        return mapToAuthorDto(baseRepository.update(authorEntity));
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