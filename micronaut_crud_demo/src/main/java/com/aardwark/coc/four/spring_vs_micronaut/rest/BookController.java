package com.aardwark.coc.four.spring_vs_micronaut.rest;

import com.aardwark.coc.four.spring_vs_micronaut.dto.BookCreationDto;
import com.aardwark.coc.four.spring_vs_micronaut.dto.BookDto;
import com.aardwark.coc.four.spring_vs_micronaut.mapper.BookCreationMapper;
import com.aardwark.coc.four.spring_vs_micronaut.mapper.BookMapper;
import com.aardwark.coc.four.spring_vs_micronaut.service.BookService;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import static io.micronaut.http.HttpHeaders.LOCATION;
import static java.lang.String.format;

@Slf4j
@Controller("/book")
@ExecuteOn(TaskExecutors.IO)
@RequiredArgsConstructor
public class BookController implements BookOperations {

    private final BookService bookService;

    private final BookMapper bookMapper;
    private final BookCreationMapper bookCreationMapper;

    @Override
    public List<BookDto> findAll(final Pageable pageable) {
        log.info("GET");
        return this.bookService.findAll(pageable)
                               .stream()
                               .map(bookMapper::toDto)
                               .toList();
    }

    @Override
    public Optional<BookDto> findById(final Long id) {
        log.info("GET {}", id);
        return this.bookService.findById(id)
                               .map(bookMapper::toDto);
    }

    @Override
    public HttpResponse<BookDto> save(BookCreationDto book) {
        log.info("POST {}", book);
        return Optional.of(book)
                       .map(this.bookCreationMapper::toEntity)
                       .map(this.bookService::save)
                       .map(this.bookMapper::toDto)
                       .map(savedBook -> HttpResponse.created(savedBook)
                                                     .header(LOCATION, format("/book/%d", savedBook.id())))
                       .orElseThrow();
    }

    @Override
    public HttpResponse<BookDto> saveWithException(BookCreationDto book) {
        log.info("POST {}", book);
        return Optional.of(book)
                       .map(this.bookCreationMapper::toEntity)
                       .map(this.bookService::save)
                       .map(this.bookMapper::toDto)
                       .map(savedBook -> HttpResponse.created(savedBook)
                                                     .header(LOCATION, format("/book/%d", savedBook.id())))
                       .orElseThrow();
    }

    @Override
    public void deleteById(final Long id) {
        log.info("DELETE {}", id);
        this.bookService.delete(id);
    }

}
