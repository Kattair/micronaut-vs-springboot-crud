package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.rest;

import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.domain.BookEntity;
import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.dto.BookCreationDto;
import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.dto.BookDto;
import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

// https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing.spring-boot-applications.with-mock-environment
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
// https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto.testing.testcontainers
@Testcontainers
class BookControllerTest {

    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql");

    @DynamicPropertySource
    static void mySqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
    }

    @Autowired
    WebTestClient client;
    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setup() {
        this.bookRepository.deleteAll();
    }

    @Test
    void givenEmptyDB_whenFindAll_returnEmptyList() {
        client.get().uri("/book")
              .exchange()
              .expectStatus().isOk()
              .expectBodyList(BookDto.class).hasSize(0);
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

        client.get().uri("/book")
              .exchange()
              .expectStatus().isOk()
              .expectBodyList(BookDto.class).hasSize(2);
    }

    @Test
    void whenBookNotFoundById_returnNotFound() {
        client.get().uri("/0")
              .exchange()
              .expectStatus().isNotFound();
    }

    @Test
    void whenBookFound_returnOk() {
        var savedBook = this.bookRepository.save(BookEntity.builder()
                                                           .isbn("isbn-1234")
                                                           .name("First book")
                                                           .build());
        client.get().uri(format("/book/%s", savedBook.getId()))
              .exchange()
              .expectStatus().isOk()
              .expectBody(BookDto.class).value(bookDto -> assertEquals(savedBook.getId(), bookDto.id()));
    }

    @Test
    void givenValidBookDto_whenSave_thenSaveIntoDB() {
        client.post().uri("/book")
              .bodyValue(new BookCreationDto("isbn", "name"))
              .exchange()
              .expectStatus().isOk()
              .expectBody(BookDto.class).value(bookDto -> assertNotNull(bookDto.id()));
    }

    @ParameterizedTest
    @CsvSource({",", ",name", "isbn,"})
    void givenInvalidBookDto_whenSave_thenBadRequest(String isbn, String name) {
        client.post().uri("/book")
              .bodyValue(new BookCreationDto(isbn, name))
              .exchange()
              .expectStatus().isBadRequest();
    }

    // TODO: add DELETE tests

}
