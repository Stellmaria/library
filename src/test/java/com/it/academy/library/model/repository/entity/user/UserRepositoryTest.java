package com.it.academy.library.model.repository.entity.user;

import com.it.academy.library.mapper.filter.user.UserFilterMapper;
import com.it.academy.library.mapper.filter.user.UserRoleFilterMapper;
import com.it.academy.library.mapper.filter.user.UserStatusFilterMapper;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("User repository test.")
class UserRepositoryTest extends IntegrationTestBase {
    private static final Integer USER_ROLE_ID_2 = 2;
    private static final Integer USER_STATUS_ID_2 = 2;
    private static final Long USER_ID_4 = 4L;
    private static final String EMAIL_EXAMPLE_COM = "email@example.com";
    private static final String TEST_GMAIL_COM = "test@gmail.com";

    private final UserRepository userRepository;

    private final UserFilterMapper userFilterMapper;
    private final UserRoleFilterMapper userRoleFilterMapper;
    private final UserStatusFilterMapper userStatusFilterMapper;

    @Test
    @DisplayName("Save user.")
    void saveUser() {
        var expectedCount = userRepository.count() + 1;
        var userRole = UserRole.builder()
                .id(USER_ROLE_ID_2)
                .build();
        var userStatus = UserStatus.builder()
                .id(USER_STATUS_ID_2)
                .build();
        var user = User.builder()
                .username(ConstantUtil.NEW + ConstantUtil.SAVE)
                .firstName(ConstantUtil.NEW + ConstantUtil.SAVE)
                .lastName(ConstantUtil.NEW + ConstantUtil.SAVE)
                .email(EMAIL_EXAMPLE_COM)
                .password(ConstantUtil.NEW + ConstantUtil.SAVE)
                .userRole(userRole)
                .userStatus(userStatus)
                .birthday(ConstantUtil.USER_BIRTHDAY)
                .build();

        var actual = userRepository.save(user);
        var actualCount = userRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getUsername()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getFirstName()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getLastName()),
                () -> assertEquals(EMAIL_EXAMPLE_COM, actual.getEmail()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getPassword()),
                () -> assertEquals(USER_ROLE_ID_2, actual.getUserRole().getId()),
                () -> assertEquals(USER_STATUS_ID_2, actual.getUserStatus().getId()),
                () -> assertEquals(ConstantUtil.USER_BIRTHDAY, actual.getBirthday())
        );
    }

    @Test
    @DisplayName("Update user.")
    void update() {
        var userRole = UserRole.builder()
                .id(USER_ROLE_ID_2)
                .build();
        var userStatus = UserStatus.builder()
                .id(USER_STATUS_ID_2)
                .build();
        var user = userRepository.findById(ConstantUtil.USER_ID_5);

        user.ifPresent(it -> {
            it.setLastName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setFirstName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setUsername(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setEmail(TEST_GMAIL_COM);
            it.setPassword(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setUserRole(userRole);
            it.setUserStatus(userStatus);
            it.setBirthday(ConstantUtil.USER_BIRTHDAY);
            userRepository.save(it);
        });
        var actual = userRepository.findById(ConstantUtil.USER_ID_5);

        actual.ifPresent(it -> {
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getUsername());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getFirstName());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getLastName());
            assertEquals(TEST_GMAIL_COM, it.getEmail());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getPassword());
            assertEquals(USER_ROLE_ID_2, it.getUserRole().getId());
            assertEquals(USER_STATUS_ID_2, it.getUserStatus().getId());
            assertEquals(ConstantUtil.USER_ID_5, it.getId());
            assertEquals(ConstantUtil.USER_BIRTHDAY, it.getBirthday());
        });
    }

    @Test
    @DisplayName("Delete user.")
    void deleteUser() {
        var expected = userRepository.count() - 1;

        userRepository.deleteById(USER_ID_4);
        var actual = userRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find all user by user role filter.")
    void findAllUserByUserRoleFilter() {
        var userRole = new UserRole();
        userRole.setName(ConstantUtil.USER_ROLE_NAME_READER);

        var actual = userRepository.findAllByUserRoleFilter(
                userRoleFilterMapper.map(userRole)
        );

        assertThat(actual).hasSize(2);
    }

    @Test
    @DisplayName("Find all user by user status filter.")
    void findAllUserByUserStatusFilter() {
        var userStatus = new UserStatus();
        userStatus.setName(ConstantUtil.USER_STATUS_NAME_GUEST);

        var actual = userRepository.findAllByUserStatusFilter(
                userStatusFilterMapper.map(userStatus)
        );

        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("Find all user by user filter.")
    void findAllUserByUserFilter() {
        var user = new User();
        user.setFirstName(ConstantUtil.USER_FIRST_NAME_SVETA);

        var actual = userRepository.findAllByUserFilter(
                userFilterMapper.map(user)
        );

        assertThat(actual).hasSize(1);
    }
}
