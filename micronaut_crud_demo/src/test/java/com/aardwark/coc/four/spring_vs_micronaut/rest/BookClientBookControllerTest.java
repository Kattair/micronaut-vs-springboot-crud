package com.aardwark.coc.four.spring_vs_micronaut.rest;

import com.aardwark.coc.four.spring_vs_micronaut.domain.BookEntity;
import com.aardwark.coc.four.spring_vs_micronaut.mapper.BookMapper;
import com.aardwark.coc.four.spring_vs_micronaut.repository.BookRepository;
import io.micronaut.data.model.Pageable;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(transactional = false)
class BookClientBookControllerTest {
    @Inject
    BookClient bookClient;
    @Inject
    BookRepository bookRepository;
    @Inject
    BookMapper bookMapper;

    @BeforeEach
    void setup() {
        this.bookRepository.deleteAll();
    }

    @Test
    void givenEmptyDB_whenFindAll_returnEmptyList() {
        var books = bookClient.findAll(Pageable.unpaged());

        assertNotNull(books);
        assertEquals(0, books.size());
    }

    @Test
    void givenFilledDB_whenFindAll_returnBookDtos() {
        var booksToSave =
                List.of(BookEntity.builder()
                                  .isbn("isbn-1234")
                                  .name("First book")
                                  .build(),
                        BookEntity.builder()
                                  .isbn("isbn-5678")
                                  .name("Second book")
                                  .build());
        bookRepository.saveAll(booksToSave);

        var books = bookClient.findAll(Pageable.unpaged());

        assertNotNull(books);
        assertEquals(2, books.size());
    }

    @Test
    void whenBookNotFound_returnOptionalEmpty() {
        var book = bookClient.findById(1L);

        assertNotNull(book);
        assertTrue(book.isEmpty());
    }

    @Test
    void whenBookFound_returnBook() {
        var bookToSave = BookEntity.builder()
                                   .isbn("isbn-1234")
                                   .name("First book")
                                   .build();
        var savedBook = bookRepository.save(bookToSave);

        var book = bookClient.findById(savedBook.getId());

        assertNotNull(book);
        assertEquals(savedBook.getId(), book.get().id());
    }

    //TODO: finish other tests if desired
}
