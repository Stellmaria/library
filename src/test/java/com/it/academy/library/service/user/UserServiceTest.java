package com.it.academy.library.service.user;

import com.it.academy.library.dto.create.user.UserCreateEditDto;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import static com.it.academy.library.util.ConstantUtil.UPDATE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
@DisplayName("User service test.")
class UserServiceTest extends IntegrationTestBase {
    private static final Long USER_ID_4 = 4L;
    private static final Long USER_ID_3 = 3L;
    private static final Long USER_ID_99 = 99L;
    private static final Integer USER_ROLE_ID_1 = 1;
    private static final Integer USER_STATUS_ID_1 = 1;
    private static final String TEST_EMAIL_EXAMPLE_COM = "test_email@example.com";
    private static final String EMAIL_TEST_EXAMPLE_COM = "email_test@example.com";

    private final UserService userService;

    @Test
    @DisplayName("Create new user.")
    void create() {
        var user = new UserCreateEditDto(
                ConstantUtil.SAVE + ConstantUtil.NEW,
                ConstantUtil.SAVE + ConstantUtil.NEW,
                ConstantUtil.SAVE + ConstantUtil.NEW,
                TEST_EMAIL_EXAMPLE_COM,
                ConstantUtil.SAVE + ConstantUtil.NEW,
                USER_ROLE_ID_1,
                USER_STATUS_ID_1,
                ConstantUtil.USER_BIRTHDAY,
                new MockMultipartFile("test", new byte[0])
        );

        var actual = userService.create(user);

        assertAll(
                () -> assertEquals(ConstantUtil.SAVE + ConstantUtil.NEW, actual.getUsername()),
                () -> assertEquals(ConstantUtil.SAVE + ConstantUtil.NEW, actual.getFirstName()),
                () -> assertEquals(ConstantUtil.SAVE + ConstantUtil.NEW, actual.getLastName()),
                () -> assertEquals(TEST_EMAIL_EXAMPLE_COM, actual.getEmail()),
                () -> assertEquals(USER_ROLE_ID_1, actual.getUserRole().getId()),
                () -> assertEquals(USER_STATUS_ID_1, actual.getUserStatus().getId()),
                () -> assertEquals(ConstantUtil.USER_BIRTHDAY, actual.getBirthday())
        );
    }

    @Test
    @DisplayName("Update user.")
    void update() {
        var user = new UserCreateEditDto(
                UPDATE + ConstantUtil.NEW,
                UPDATE + ConstantUtil.NEW,
                UPDATE + ConstantUtil.NEW,
                EMAIL_TEST_EXAMPLE_COM,
                UPDATE + ConstantUtil.NEW,
                USER_ROLE_ID_1,
                USER_STATUS_ID_1,
                ConstantUtil.USER_BIRTHDAY,
                new MockMultipartFile("test", new byte[0])
        );

        var actual = userService.update(USER_ID_4, user);

        actual.ifPresent(it -> {
            assertEquals(UPDATE + ConstantUtil.NEW, actual.get().getUsername());
            assertEquals(UPDATE + ConstantUtil.NEW, actual.get().getFirstName());
            assertEquals(UPDATE + ConstantUtil.NEW, actual.get().getLastName());
            assertEquals(EMAIL_TEST_EXAMPLE_COM, actual.get().getEmail());
            assertEquals(USER_ROLE_ID_1, actual.get().getUserRole().getId());
            assertEquals(USER_STATUS_ID_1, actual.get().getUserStatus().getId());
            assertEquals(ConstantUtil.USER_BIRTHDAY, actual.get().getBirthday());
        });
    }

    @Test
    @DisplayName("Delete user.")
    void delete() {
        assertAll(
                () -> assertTrue(userService.delete(USER_ID_3)),
                () -> assertFalse(userService.delete(USER_ID_99))
        );
    }
}
