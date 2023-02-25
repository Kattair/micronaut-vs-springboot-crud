package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.mapper;

import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.domain.BookEntity;
import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.dto.BookDto;

public interface BookMapper {
    BookDto toDto(final BookEntity entity);
    BookEntity toEntity(final BookDto dto);
}
