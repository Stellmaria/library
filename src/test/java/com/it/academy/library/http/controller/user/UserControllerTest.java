package com.it.academy.library.http.controller.user;

import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.it.academy.library.dto.create.user.UserCreateEditDto.Fields.birthday;
import static com.it.academy.library.dto.create.user.UserCreateEditDto.Fields.email;
import static com.it.academy.library.dto.create.user.UserCreateEditDto.Fields.firstName;
import static com.it.academy.library.dto.create.user.UserCreateEditDto.Fields.lastName;
import static com.it.academy.library.dto.create.user.UserCreateEditDto.Fields.login;
import static com.it.academy.library.dto.create.user.UserCreateEditDto.Fields.password;
import static com.it.academy.library.dto.create.user.UserCreateEditDto.Fields.userRoleId;
import static com.it.academy.library.dto.create.user.UserCreateEditDto.Fields.userStatusId;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RequiredArgsConstructor
@AutoConfigureMockMvc
class UserControllerTest extends IntegrationTestBase {
    private final MockMvc mockMvc;

    @SneakyThrows
    @Test
    @DisplayName("Find all users.")
    void findAllUser() {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"));
    }

    @SneakyThrows
    @Test
    @DisplayName("Create user.")
    void createUser() {
        mockMvc.perform(post("/users")
                        .param(login, "login")
                        .param(firstName, "firstName")
                        .param(lastName, "lastName")
                        .param(email, "email@gmail.com")
                        .param(password, "123456")
                        .param(userRoleId, "1")
                        .param(userStatusId, "2")
                        .param(birthday, "2000-05-03"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }
}