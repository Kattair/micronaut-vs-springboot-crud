package com.aardwark.coc.four.spring_vs_micronaut.repository;

import com.aardwark.coc.four.spring_vs_micronaut.domain.BookEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;

@Repository
public interface BookRepository extends PageableRepository<BookEntity, Long> {
}
