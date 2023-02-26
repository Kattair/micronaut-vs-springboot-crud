package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.rest;

import com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo.config.ApplicationConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("dev")
@Slf4j
@Validated
@RestController
@RequestMapping("/dev")
@RequiredArgsConstructor
public class DevEnvironmentController {

    private final Environment environment;
    private final ApplicationConfig applicationConfig;

    @GetMapping("/profiles")
    public String[] getActiveProfiles() {
        return environment.getActiveProfiles();
    }

    @GetMapping("/appcfg")
    public ApplicationConfig getApplicationConfig() {
        return applicationConfig;
    }
}
