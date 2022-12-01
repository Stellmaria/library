package com.it.academy.library.service.entity.user.impl;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.model.repository.entity.user.UserRepository;
import com.it.academy.library.service.dto.create.UserCreateEditDto;
import com.it.academy.library.service.dto.filter.user.UserFilter;
import com.it.academy.library.service.entity.user.UserService;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

import static com.it.academy.library.util.ConstantUtil.USER_ROLE_ID_3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
@DisplayName("User service test.")
class UserServiceImplTest extends IntegrationTestBase {


    private final UserService userService;

    private final UserRepository userRepository;

    @Test
    @DisplayName("Load user.")
    void loadUserByUsername() {
        var expected = new User(
                ConstantUtil.USER_USERNAME_READER,
                ConstantUtil.USER_READER_PASSWORD,
                Collections.singleton(ConstantUtil.getUserRole(USER_ROLE_ID_3, ConstantUtil.USER_ROLE_READER))
        );

        var actual = userService.loadUserByUsername(ConstantUtil.USER_USERNAME_READER);

        assertAll(
                () -> assertEquals(expected.getUsername(), actual.getUsername()),
                () -> assertEquals(expected.getPassword(), actual.getPassword()),
                () -> assertEquals(expected.getAuthorities(), actual.getAuthorities())
        );
    }

    @Nested
    @DisplayName("CRUD methods.")
    class CRUD {
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
                    new MockMultipartFile(ConstantUtil.NAME_IMAGE_TEST, new byte[0])
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
        @DisplayName("Update user.")
        void update() {
            var user = new UserCreateEditDto(
                    ConstantUtil.UPDATE + ConstantUtil.NEW,
                    ConstantUtil.UPDATE + ConstantUtil.NEW,
                    ConstantUtil.UPDATE + ConstantUtil.NEW,
                    ConstantUtil.USER_EMAIL_TEST_GMAIL_COM,
                    ConstantUtil.UPDATE + ConstantUtil.NEW,
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
                            () -> assertEquals(ConstantUtil.UPDATE + ConstantUtil.NEW, entity.getUsername()),
                            () -> assertEquals(ConstantUtil.UPDATE + ConstantUtil.NEW, entity.getFirstName()),
                            () -> assertEquals(ConstantUtil.UPDATE + ConstantUtil.NEW, entity.getLastName()),
                            () -> assertEquals(ConstantUtil.USER_EMAIL_TEST_GMAIL_COM, entity.getEmail()),
                            () -> assertEquals(ConstantUtil.USER_ROLE_ID_1, entity.getUserRole().getId()),
                            () -> assertEquals(ConstantUtil.USER_STATUS_ID_1, entity.getUserStatus().getId()),
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

    @Test
    @DisplayName("Find user by id.")
    void findById() {
        var expected = ConstantUtil.getUserSveta();

        var actual = userService.findById(expected.getId());

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(expected.getId(), entity.getId()),
                        () -> assertEquals(expected.getUsername(), entity.getUsername()),
                        () -> assertEquals(expected.getFirstName(), entity.getFirstName()),
                        () -> assertEquals(expected.getLastName(), entity.getLastName()),
                        () -> assertEquals(expected.getImage(), entity.getImage()),
                        () -> assertEquals(expected.getEmail(), entity.getEmail()),
                        () -> assertEquals(expected.getPassword(), entity.getPassword()),
                        () -> assertEquals(expected.getUserRole(), entity.getUserRole()),
                        () -> assertEquals(expected.getUserStatus(), entity.getUserStatus()),
                        () -> assertEquals(expected.getBirthday(), entity.getBirthday())
                )
        );
    }

    @Test
    @DisplayName("Find all user.")
    void findAll() {
        var expected = userRepository.count();

        var actual = userService.findAll();

        assertThat(actual).hasSize(Math.toIntExact(expected));
    }

    @Test
    @DisplayName("Find all user with filter.")
    void findAllWithFilter() {
        var expected = 3;
        var filter = new UserFilter();
        filter.setLastName(ConstantUtil.USER_LAST_NAME_FRAGMENT_OV);

        var actual = userService.findAll(filter, Pageable.ofSize(ConstantUtil.PAGE_SIZE));

        assertThat(actual).hasSize(expected);
    }

    @Test
    void findByUsername() {
        var expected = ConstantUtil.getUserSveta();

        var actual = userService.findByUsername(expected.getUsername());

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(expected.getId(), entity.getId()),
                        () -> assertEquals(expected.getUsername(), entity.getUsername()),
                        () -> assertEquals(expected.getFirstName(), entity.getFirstName()),
                        () -> assertEquals(expected.getLastName(), entity.getLastName()),
                        () -> assertEquals(expected.getImage(), entity.getImage()),
                        () -> assertEquals(expected.getEmail(), entity.getEmail()),
                        () -> assertEquals(expected.getPassword(), entity.getPassword()),
                        () -> assertEquals(expected.getUserRole(), entity.getUserRole()),
                        () -> assertEquals(expected.getUserStatus(), entity.getUserStatus()),
                        () -> assertEquals(expected.getBirthday(), entity.getBirthday())
                )
        );
    }


    @Test
    void findByEmail() {
        var expected = ConstantUtil.getUserSveta();

        var actual = userService.findByEmail(expected.getEmail());

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(expected.getId(), entity.getId()),
                        () -> assertEquals(expected.getUsername(), entity.getUsername()),
                        () -> assertEquals(expected.getFirstName(), entity.getFirstName()),
                        () -> assertEquals(expected.getLastName(), entity.getLastName()),
                        () -> assertEquals(expected.getImage(), entity.getImage()),
                        () -> assertEquals(expected.getEmail(), entity.getEmail()),
                        () -> assertEquals(expected.getPassword(), entity.getPassword()),
                        () -> assertEquals(expected.getUserRole(), entity.getUserRole()),
                        () -> assertEquals(expected.getUserStatus(), entity.getUserStatus()),
                        () -> assertEquals(expected.getBirthday(), entity.getBirthday())
                )
        );
    }

    @Test
    @DisplayName("Find book image.")
    void findImage() {
        var expected = 2716;

        var actual = userService.findAvatar(1L);

        actual.ifPresent(entity -> assertEquals(expected, entity.length));
    }
}
