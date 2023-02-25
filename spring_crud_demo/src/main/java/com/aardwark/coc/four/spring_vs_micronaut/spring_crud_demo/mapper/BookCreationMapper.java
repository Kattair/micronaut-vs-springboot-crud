package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.mapper;

import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.domain.BookEntity;
import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.dto.BookCreationDto;

public interface BookCreationMapper {
    BookEntity toEntity(final BookCreationDto dto);
}
