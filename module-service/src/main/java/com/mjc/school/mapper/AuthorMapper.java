package com.mjc.school.mapper;

import com.mjc.school.model.impl.AuthorEntity;
import com.mjc.school.dto.AuthorDto;
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
