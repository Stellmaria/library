package com.it.academy.library.service.entity.user.impl;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.service.dto.create.UserCreateEditDto;
import com.it.academy.library.service.entity.user.UserService;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.it.academy.library.util.ConstantUtil.UPDATE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
@DisplayName("User service test.")
class UserServiceImplTest extends IntegrationTestBase {
    private final UserService userService;

    @Test
    void loadUserByUsername() {
    }

    @Test
    @DisplayName("Create new user.")
    void create() {
        var user = new UserCreateEditDto(
                ConstantUtil.SAVE + ConstantUtil.NEW,
                ConstantUtil.SAVE + ConstantUtil.NEW,
                ConstantUtil.SAVE + ConstantUtil.NEW,
                ConstantUtil.USER_EMAIL_TEST_GMAIL_COM,
                ConstantUtil.SAVE + ConstantUtil.NEW,
                ConstantUtil.USER_ROLE_ID_1,
                ConstantUtil.USER_STATUS_ID_1,
                ConstantUtil.USER_BIRTHDAY,
                new MockMultipartFile("test", new byte[0])
        );

        var actual = userService.create(user);

        assertAll(
                () -> assertEquals(ConstantUtil.SAVE + ConstantUtil.NEW, actual.getUsername()),
                () -> assertEquals(ConstantUtil.SAVE + ConstantUtil.NEW, actual.getFirstName()),
                () -> assertEquals(ConstantUtil.SAVE + ConstantUtil.NEW, actual.getLastName()),
                () -> assertEquals(ConstantUtil.USER_EMAIL_TEST_GMAIL_COM, actual.getEmail()),
                () -> assertEquals(ConstantUtil.USER_ROLE_ID_1, actual.getUserRole().getId()),
                () -> assertEquals(ConstantUtil.USER_STATUS_ID_1, actual.getUserStatus().getId()),
                () -> assertEquals(ConstantUtil.USER_BIRTHDAY, actual.getBirthday())
        );
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void testFindAll() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    @DisplayName("Update user.")
    void update() {
        var user = new UserCreateEditDto(
                UPDATE + ConstantUtil.NEW,
                UPDATE + ConstantUtil.NEW,
                UPDATE + ConstantUtil.NEW,
                ConstantUtil.USER_EMAIL_TEST_GMAIL_COM,
                UPDATE + ConstantUtil.NEW,
                ConstantUtil.USER_ROLE_ID_1,
                ConstantUtil.USER_STATUS_ID_1,
                ConstantUtil.USER_BIRTHDAY,
                new MockMultipartFile(ConstantUtil.NAME_IMAGE_TEST, new byte[0])
        );

        var actual = userService.update(
                ConstantUtil.USER_ID_4, user, SecurityContextHolder.getContext().getAuthentication()
        );

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(UPDATE + ConstantUtil.NEW, entity.getUsername()),
                        () -> assertEquals(UPDATE + ConstantUtil.NEW, entity.getFirstName()),
                        () -> assertEquals(UPDATE + ConstantUtil.NEW, entity.getLastName()),
                        () -> assertEquals(ConstantUtil.USER_EMAIL_TEST_GMAIL_COM, entity.getEmail()),
                        () -> assertEquals(3, entity.getUserRole().getId()),
                        () -> assertEquals(3, entity.getUserStatus().getId()),
                        () -> assertEquals(ConstantUtil.USER_BIRTHDAY, entity.getBirthday())
                )
        );
    }

    @Test
    @DisplayName("Delete user.")
    void delete() {
        assertAll(
                () -> assertTrue(userService.delete(ConstantUtil.USER_ID_3)),
                () -> assertFalse(userService.delete(ConstantUtil.USER_ID_99))
        );
    }
}