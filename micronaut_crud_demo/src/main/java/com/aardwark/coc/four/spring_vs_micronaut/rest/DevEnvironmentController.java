package com.aardwark.coc.four.spring_vs_micronaut.rest;

import com.aardwark.coc.four.spring_vs_micronaut.config.ApplicationConfig;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Requires(env = "dev")
@Slf4j
@Validated
@Controller("/dev")
@RequiredArgsConstructor
public class DevEnvironmentController {

    private final Environment environment;
    private final ApplicationConfig applicationConfig;

    @Get("/env")
    public Set<String> getActiveEnvironments() {
        return environment.getActiveNames();
    }

    @Get("/appcfg")
    public ApplicationConfig getApplicationConfig() {
        return applicationConfig;
    }

}
