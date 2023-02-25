package com.aardwark.coc.four.spring_vs_micronaut.mapper;

import com.aardwark.coc.four.spring_vs_micronaut.domain.BookEntity;
import com.aardwark.coc.four.spring_vs_micronaut.dto.BookCreationDto;

public interface BookCreationMapper {
    BookEntity toEntity(final BookCreationDto dto);
}
