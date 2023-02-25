package com.aardwark.coc.four.spring_vs_micronaut.mapper;

import com.aardwark.coc.four.spring_vs_micronaut.domain.BookEntity;
import com.aardwark.coc.four.spring_vs_micronaut.dto.BookCreationDto;
import jakarta.inject.Singleton;

@Singleton
public class DefaultBookCreationMapper implements BookCreationMapper {
    @Override
    public BookEntity toEntity(BookCreationDto dto) {
        return BookEntity.builder()
                         .isbn(dto.isbn())
                         .name(dto.name())
                         .build();
    }
}
