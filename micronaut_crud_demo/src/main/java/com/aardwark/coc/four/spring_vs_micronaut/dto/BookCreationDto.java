package com.aardwark.coc.four.spring_vs_micronaut.dto;

import io.micronaut.serde.annotation.Serdeable;

import javax.validation.constraints.NotBlank;

@Serdeable
public record BookCreationDto(
        @NotBlank String isbn,
        @NotBlank String name
) {
}
