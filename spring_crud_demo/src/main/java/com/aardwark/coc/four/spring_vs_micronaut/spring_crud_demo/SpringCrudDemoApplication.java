package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringCrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudDemoApplication.class, args);
	}

}
