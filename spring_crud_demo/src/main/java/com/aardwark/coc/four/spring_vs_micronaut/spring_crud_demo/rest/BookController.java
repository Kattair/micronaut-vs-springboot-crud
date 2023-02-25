package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.rest;

import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.dto.BookCreationDto;
import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.dto.BookDto;
import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.mapper.BookCreationMapper;
import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.mapper.BookMapper;
import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.http.HttpHeaders.LOCATION;

@Slf4j
@Validated
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;
    private final BookCreationMapper bookCreationMapper;

    @GetMapping
    public List<BookDto> findAll(final Pageable pageable) {
        log.info("GET");
        return this.bookService.findAll(pageable)
                               .stream()
                               .map(this.bookMapper::toDto)
                               .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(final @PathVariable @NotNull Long id) {
        log.info("GET {}", id);
        return this.bookService.findById(id)
                               .map(this.bookMapper::toDto)
                               .map(ResponseEntity::ok)
                               .orElse(ResponseEntity.notFound()
                                                     .build());
    }

    @PostMapping
    public ResponseEntity<BookDto> save(@RequestBody @Valid BookCreationDto book) {
        log.info("POST {}", book);
        return Optional.of(book)
                       .map(this.bookCreationMapper::toEntity)
                       .map(this.bookService::save)
                       .map(this.bookMapper::toDto)
                       .map(savedBook -> ResponseEntity.ok()
                                                       .header(LOCATION, format("/book/%d", savedBook.id()))
                                                       .body(savedBook))
                       .orElseThrow();
    }

    @PostMapping("/ex")
    public ResponseEntity<BookDto> saveWithException(@RequestBody @Valid BookCreationDto book) {
        log.info("POST {}", book);
        return Optional.of(book)
                       .map(this.bookCreationMapper::toEntity)
                       .map(this.bookService::save)
                       .map(this.bookMapper::toDto)
                       .map(savedBook -> ResponseEntity.ok()
                                                       .header(LOCATION, format("/book/%d", savedBook.id()))
                                                       .body(savedBook))
                       .orElseThrow();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable @NotNull final Long id) {
        log.info("DELETE {}", id);
        this.bookService.delete(id);
    }
}
