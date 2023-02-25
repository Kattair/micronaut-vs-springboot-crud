package com.aardwark.coc.four.spring_vs_micronaut.rest;

import com.aardwark.coc.four.spring_vs_micronaut.dto.BookCreationDto;
import com.aardwark.coc.four.spring_vs_micronaut.dto.BookDto;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Validated
public interface BookOperations {
    @Get
    List<BookDto> findAll(final Pageable pageable);

    @Get("/{id}")
    Optional<BookDto> findById(@PathVariable @NotNull final Long id);

    @Post
    HttpResponse<BookDto> save(@Body @Valid BookCreationDto book);

    /**
     * Purpose is to throw an exception for testing purposes.
     */
    @Post("/ex")
    HttpResponse<BookDto> saveWithException(@Body @Valid BookCreationDto book);

    @Delete("{id}")
    void deleteById(@PathVariable @NotNull final Long id);
}
