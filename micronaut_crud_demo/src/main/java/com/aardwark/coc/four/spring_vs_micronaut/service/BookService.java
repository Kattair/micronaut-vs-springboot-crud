package com.aardwark.coc.four.spring_vs_micronaut.service;

import com.aardwark.coc.four.spring_vs_micronaut.domain.BookEntity;
import com.aardwark.coc.four.spring_vs_micronaut.repository.BookRepository;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Optional<BookEntity> findById(final Long id) {
        return this.bookRepository.findById(id);
    }

    public List<BookEntity> findAll(final Pageable pageable) {
        return this.bookRepository.findAll(pageable).getContent();
    }

    public BookEntity save(BookEntity book) {
        return this.bookRepository.save(book);
    }

    public BookEntity saveWithException(BookEntity book) {
        this.save(book);
        throw new DataAccessException("test exception");
    }

    public void delete(final Long id) {
        this.bookRepository.deleteById(id);
    }

}
