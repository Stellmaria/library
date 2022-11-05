package com.it.academy.library.model.repository.entity;

import com.it.academy.library.annotation.IT;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@IT
@WithMockUser(username = "test", password = "test", authorities = {"Admin", "User"})
public abstract class IntegrationTestBase {
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15.0");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(@NotNull DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }
}
