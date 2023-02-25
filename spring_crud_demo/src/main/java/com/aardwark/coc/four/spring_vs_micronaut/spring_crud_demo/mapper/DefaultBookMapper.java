package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.mapper;

import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.domain.BookEntity;
import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.dto.BookDto;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DefaultBookMapper implements BookMapper {

    @Override
    public BookDto toDto(BookEntity entity) {
        return new BookDto(entity.getId(),
                           entity.getIsbn(),
                           entity.getName());
    }

    @Override
    public BookEntity toEntity(BookDto dto) {
        return BookEntity.builder()
                         .id(dto.id())
                         .isbn(dto.isbn())
                         .name(dto.name())
                         .createdAt(Instant.now())
                         .build();
    }

}
