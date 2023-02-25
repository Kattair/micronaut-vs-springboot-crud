package com.aardwark.coc.four.spring_vs_micronaut.dto;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

import javax.validation.constraints.NotBlank;

@Serdeable
public record BookDto(
        @Nullable Long id,
        @NotBlank String isbn,
        @NotBlank String name
) {}
