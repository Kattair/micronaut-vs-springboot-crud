package com.aardwark.coc.four.spring_vs_micronaut.mapper;

import com.aardwark.coc.four.spring_vs_micronaut.domain.BookEntity;
import com.aardwark.coc.four.spring_vs_micronaut.dto.BookDto;
import jakarta.inject.Singleton;

import java.time.Instant;

@Singleton
public class DefaultBookMapper implements BookMapper {

    @Override
    public BookDto toDto(BookEntity entity) {
        if (entity == null) return null;

        return new BookDto(entity.getId(),
                           entity.getIsbn(),
                           entity.getName());
    }

    @Override
    public BookEntity toEntity(BookDto dto) {
        if (dto == null) return null;
        
        return BookEntity.builder()
                         .id(dto.id())
                         .isbn(dto.isbn())
                         .name(dto.name())
                         .createdAt(Instant.now())
                         .build();
    }
    
}
