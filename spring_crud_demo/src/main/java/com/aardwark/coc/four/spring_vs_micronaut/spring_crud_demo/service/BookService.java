package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.service;

import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.domain.BookEntity;
import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        throw new RuntimeException("test exception");
    }

    public void delete(final Long id) {
        this.bookRepository.deleteById(id);
    }
}
