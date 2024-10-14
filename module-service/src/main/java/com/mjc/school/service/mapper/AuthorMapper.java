package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.NewsEntity;
import com.mjc.school.service.dto.NewsDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    NewsDto newsEntityToNewsDto(NewsEntity newsEntity);

    NewsEntity newsDtoToNewsEntity(NewsDto newsDto);
}
