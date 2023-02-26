package com.aardwark.coc.four.spring_vs_micronaut.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Context;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Getter
@Setter
// @Context since @Singleton is lazy, and we will see a validation error only after trying to inject
@Context
@Serdeable
@ConfigurationProperties("app")
public class ApplicationConfig {

    @NotBlank
    private String message = "Default message";

    // must have the same name as the @ConfigurationProperties below to be filled
    private NestedConfig nestedConfig;

    @Getter
    @Setter
    @Serdeable
    @ConfigurationProperties("nested-config")
    public static class NestedConfig {
        private Optional<Integer> count = Optional.empty();
    }
}
