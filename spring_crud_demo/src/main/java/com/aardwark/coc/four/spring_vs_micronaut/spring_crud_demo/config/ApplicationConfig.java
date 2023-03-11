package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties("app")
public class ApplicationConfig {

    @NotBlank
    private String message = "Default message";

    private NestedConfig nestedConfig;

    @Getter
    @Setter
    public static class NestedConfig {
        private Integer count;
    }
}
