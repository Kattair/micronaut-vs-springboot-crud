package com.aardwark.coc.four.spring_vs_micronaut.spring_crud_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
class SpringCrudDemoApplicationTests {

	@Container
	private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql");

	@DynamicPropertySource
	static void mySqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
		registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
		registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
	}

	@Test
	void contextLoads() {
		assertTrue(MY_SQL_CONTAINER.isRunning());
	}

}
