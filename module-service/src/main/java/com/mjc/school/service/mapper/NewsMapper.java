package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.NewsEntity;
import com.mjc.school.service.dto.NewsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDto newsEntityToNewsDto(NewsEntity newsEntity);

    @Mapping(target = "id", ignore = true)
    NewsEntity newsDtoToNewsEntity(NewsDto newsDto);
}
