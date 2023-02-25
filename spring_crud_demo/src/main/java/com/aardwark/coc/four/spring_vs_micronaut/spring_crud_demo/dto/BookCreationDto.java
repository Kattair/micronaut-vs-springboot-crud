package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.dto;

import jakarta.validation.constraints.NotBlank;

public record BookCreationDto(
        @NotBlank String isbn,
        @NotBlank String name
) {
}
