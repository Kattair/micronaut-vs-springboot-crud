package com.aardwark.coc.four.spring_vs_micronaut.rest;

import com.aardwark.coc.four.spring_vs_micronaut.domain.BookEntity;
import com.aardwark.coc.four.spring_vs_micronaut.dto.BookCreationDto;
import com.aardwark.coc.four.spring_vs_micronaut.dto.BookDto;
import com.aardwark.coc.four.spring_vs_micronaut.repository.BookRepository;
import io.micronaut.core.type.Argument;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static io.micronaut.http.HttpRequest.GET;
import static io.micronaut.http.HttpRequest.POST;
import static io.micronaut.http.HttpStatus.BAD_REQUEST;
import static io.micronaut.http.HttpStatus.CREATED;
import static io.micronaut.http.HttpStatus.NOT_FOUND;
import static io.micronaut.http.HttpStatus.OK;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest(transactional = false)
class HttpClientBookControllerTest {

    private BlockingHttpClient blockingHttpClient;

    @Inject
    @Client("/book")
    HttpClient httpClient;
    @Inject
    BookRepository bookRepository;

    @BeforeEach
    void setup() {
        if (isNull(this.blockingHttpClient)) {
            this.blockingHttpClient = httpClient.toBlocking();
        }

        this.bookRepository.deleteAll();
    }

    @Test
    void givenEmptyDB_whenFindAll_returnEmptyList() {
        var request = GET("");
        var bodyType = Argument.of(List.class, BookDto.class);

        var response = this.blockingHttpClient.exchange(request, bodyType);

        assertEquals(OK, response.getStatus());
        assertEquals(0, response.getBody().get().size());
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
        this.bookRepository.saveAll(booksToSave);

        var request = GET("");
        var bodyType = Argument.of(List.class, BookDto.class);

        var response = this.blockingHttpClient.exchange(request, bodyType);

        assertEquals(OK, response.getStatus());
        assertEquals(2, response.getBody().get().size());
    }

    @Test
    void whenBookNotFoundById_returnNotFound() {
        var request = GET("/0");
        var exception = assertThrows(HttpClientResponseException.class,
                                     () -> this.blockingHttpClient.exchange(request, BookDto.class));

        assertEquals(NOT_FOUND, exception.getStatus());
    }

    @Test
    void whenBookFound_returnOk() {
        var savedBook = this.bookRepository.save(BookEntity.builder()
                                                           .isbn("isbn-1234")
                                                           .name("First book")
                                                           .build());
        var request = GET(format("/%s", savedBook.getId()));

        var response = this.blockingHttpClient.exchange(request, BookDto.class);

        assertEquals(OK, response.getStatus());
        assertEquals(savedBook.getId(), response.getBody().get().id());
    }

    @Test
    void givenValidBookDto_whenSave_thenSaveIntoDB() {
        var request = POST("/", new BookCreationDto("isbn", "name"));

        var response = this.blockingHttpClient.exchange(request, BookDto.class);

        assertEquals(CREATED, response.getStatus());
        assertNotNull(response.getBody().get().id());
    }

    @ParameterizedTest
    @CsvSource({",", ",name", "isbn,"})
    void givenInvalidBookDto_whenSave_thenBadRequest(String isbn, String name) {
        var request = POST("/", new BookCreationDto(isbn, name));

        var exception = assertThrows(HttpClientResponseException.class,
                                    () -> this.blockingHttpClient.exchange(request, BookDto.class));

        assertEquals(BAD_REQUEST, exception.getStatus());
    }

    // TODO: add DELETE tests

}
