package com.mjc.school.service.impl;

import com.mjc.school.service.request.AuthorRequest;
import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorEntity;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validation.ValidateId;
import com.mjc.school.service.validation.ValidateLength;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthorService implements BaseService<AuthorRequest, AuthorDto, Long> {
    private final String CHECK_AUTHOR_ID = "CHECK_AUTHOR_ID";
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
    @ValidateId(type = CHECK_AUTHOR_ID)
    public AuthorDto readById(Long id) {
        return baseRepository.readById(id)
                .map(this::mapToAuthorDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    @ValidateLength
    public AuthorDto create(AuthorRequest createRequest) {
        AuthorDto authorDto = new AuthorDto(
                createRequest.getName(),
                LocalDateTime.now(),
                LocalDateTime.now());
        authorDto.setId(baseRepository.create(mapToAuthorEntity(authorDto)).getId());
        return authorDto;
    }

    @Override
    @ValidateId
    public AuthorDto update(AuthorRequest updateRequest) {
        AuthorEntity authorEntity = baseRepository.readById(updateRequest.getId()).orElseThrow();
        authorEntity.setName(updateRequest.getName());
        authorEntity.setLastUpdatedDate(LocalDateTime.now());
        return mapToAuthorDto(baseRepository.update(authorEntity));
    }

    @Override
    @ValidateId(type = CHECK_AUTHOR_ID)
    public boolean deleteById(Long id) {
        return baseRepository.deleteById(id);
    }

    private AuthorDto mapToAuthorDto(AuthorEntity authorEntity) {
        return AuthorMapper.INSTANCE.authorEntityToAuthorDto(authorEntity);
    }

    private AuthorEntity mapToAuthorEntity(AuthorDto authorDto) {
        return AuthorMapper.INSTANCE.authorDtoToAuthorEntity(authorDto);
    }
}
