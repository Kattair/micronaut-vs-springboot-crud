package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
class SpringCrudDemoApplicationTests {

	@Container
	private static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>(PostgreSQLContainer.IMAGE);

	@DynamicPropertySource
	static void mySqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
		registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
		registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);
	}

	@Test
	void contextLoads() {
		assertTrue(POSTGRES_CONTAINER.isRunning());
	}

}
