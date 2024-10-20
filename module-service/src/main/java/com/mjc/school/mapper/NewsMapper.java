package com.mjc.school.mapper;

import com.mjc.school.model.impl.NewsEntity;
import com.mjc.school.dto.NewsDto;
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
