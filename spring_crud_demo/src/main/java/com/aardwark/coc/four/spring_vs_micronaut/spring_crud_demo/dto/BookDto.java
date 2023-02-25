package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.dto;

import jakarta.validation.constraints.NotBlank;

public record BookDto(
        Long id,
        @NotBlank String isbn,
        @NotBlank String name
) {
}
