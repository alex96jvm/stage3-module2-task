package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.AuthorEntity;
import com.mjc.school.service.dto.AuthorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto authorEntityToAuthorDto(AuthorEntity authorEntity);

    @Mapping(target = "id", ignore = true)
    AuthorEntity authorDtoToAuthorEntity(AuthorDto authorDto);
}
