package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.mapper;

import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.domain.BookEntity;
import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.dto.BookCreationDto;
import org.springframework.stereotype.Component;

@Component
public class DefaultBookCreationMapper implements BookCreationMapper {
    @Override
    public BookEntity toEntity(BookCreationDto dto) {
        return BookEntity.builder()
                         .isbn(dto.isbn())
                         .name(dto.name())
                         .build();
    }
}
