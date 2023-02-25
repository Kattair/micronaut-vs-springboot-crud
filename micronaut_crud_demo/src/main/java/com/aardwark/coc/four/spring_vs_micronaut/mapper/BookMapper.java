package com.aardwark.coc.four.spring_vs_micronaut.mapper;

import com.aardwark.coc.four.spring_vs_micronaut.domain.BookEntity;
import com.aardwark.coc.four.spring_vs_micronaut.dto.BookDto;

public interface BookMapper {
    BookDto toDto(final BookEntity entity);
    BookEntity toEntity(final BookDto dto);
}
