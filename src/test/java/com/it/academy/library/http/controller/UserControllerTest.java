package com.it.academy.library.http.controller;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.it.academy.library.service.dto.create.UserCreateEditDto.Fields.email;
import static com.it.academy.library.service.dto.create.UserCreateEditDto.Fields.firstName;
import static com.it.academy.library.service.dto.create.UserCreateEditDto.Fields.lastName;
import static com.it.academy.library.service.dto.create.UserCreateEditDto.Fields.rawPassword;
import static com.it.academy.library.service.dto.create.UserCreateEditDto.Fields.username;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RequiredArgsConstructor
@DisplayName("User controller test.")
@AutoConfigureMockMvc
class UserControllerTest extends IntegrationTestBase {
    public static final String USER_PASSWORD_TEST = "test";
    public static final String USER_USERNAME_TEST = "test";

    private final MockMvc mockMvc;

    @SneakyThrows
    @Test
    @DisplayName("Save user.")
    void create() {
        mockMvc.perform(post("/users")
                        .param(username, USER_USERNAME_TEST)
                        .param(firstName, ConstantUtil.USER_FIRST_NAME_SVETA)
                        .param(lastName, ConstantUtil.USER_LAST_NAME_SVETIKOVA)
                        .param(email, ConstantUtil.USER_EMAIL_EMAIL_EXAMPLE_COM)
                        .param(rawPassword, USER_PASSWORD_TEST)
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/login")
                );
    }

    @SneakyThrows
    @Test
    @DisplayName("Not save user.")
    void notCreate() {
        mockMvc.perform(post("/users")
                        .param(username, ConstantUtil.USER_USERNAME_READER)
                        .param(firstName, ConstantUtil.USER_FIRST_NAME_SVETA)
                        .param(lastName, ConstantUtil.USER_LAST_NAME_SVETIKOVA)
                        .param(email, ConstantUtil.USER_EMAIL_EMAIL_EXAMPLE_COM)
                        .param(rawPassword, USER_PASSWORD_TEST)
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/registration")
                );
    }

    @SneakyThrows
    @Test
    @DisplayName("Find all user.")
    void findAll() {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("allUsers"))
                .andExpect(model().attributeExists("filter"));
    }

    @SneakyThrows
    @Test
    @DisplayName("Find user by id.")
    void findById() {
        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/user"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attributeExists("statuses"))
                .andExpect(model().attributeExists("users"));
    }

    @SneakyThrows
    @Test
    @DisplayName("Not find user by id.")
    void notFindById() {
        mockMvc.perform(get("/users/{id}", 99))
                .andExpect(status().isNotFound());
    }
}