package com.it.academy.library.model.repository.entity.user;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.mapper.filter.user.UserFilterMapper;
import com.it.academy.library.mapper.filter.user.UserRoleFilterMapper;
import com.it.academy.library.mapper.filter.user.UserStatusFilterMapper;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.model.entity.user.UserStatus;
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
    private final UserRepository userRepository;

    private final UserFilterMapper userFilterMapper;
    private final UserRoleFilterMapper userRoleFilterMapper;
    private final UserStatusFilterMapper userStatusFilterMapper;

    @Test
    @DisplayName("Save user.")
    void saveUser() {
        var expectedCount = userRepository.count() + 1;
        var user = User.builder()
                .username(ConstantUtil.NEW + ConstantUtil.SAVE)
                .firstName(ConstantUtil.NEW + ConstantUtil.SAVE)
                .lastName(ConstantUtil.NEW + ConstantUtil.SAVE)
                .email(ConstantUtil.USER_EMAIL_EMAIL_EXAMPLE_COM)
                .password(ConstantUtil.NEW + ConstantUtil.SAVE)
                .userRole(ConstantUtil.getUserRole())
                .userStatus(ConstantUtil.getUserStatus())
                .birthday(ConstantUtil.USER_BIRTHDAY)
                .build();

        var actual = userRepository.save(user);
        var actualCount = userRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(user.getUsername(), actual.getUsername()),
                () -> assertEquals(user.getFirstName(), actual.getFirstName()),
                () -> assertEquals(user.getLastName(), actual.getLastName()),
                () -> assertEquals(user.getEmail(), actual.getEmail()),
                () -> assertEquals(user.getPassword(), actual.getPassword()),
                () -> assertEquals(user.getUserRole().getId(), actual.getUserRole().getId()),
                () -> assertEquals(user.getUserStatus().getId(), actual.getUserStatus().getId()),
                () -> assertEquals(user.getBirthday(), actual.getBirthday())
        );
    }

    @Test
    @DisplayName("Update user.")
    void update() {
        var user = userRepository.findById(ConstantUtil.USER_ID_5);

        user.ifPresent(entity -> {
            entity.setLastName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            entity.setFirstName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            entity.setUsername(ConstantUtil.NEW + ConstantUtil.UPDATE);
            entity.setEmail(ConstantUtil.USER_EMAIL_TEST_GMAIL_COM);
            entity.setPassword(ConstantUtil.NEW + ConstantUtil.UPDATE);
            entity.setUserRole(ConstantUtil.getUserRole());
            entity.setUserStatus(ConstantUtil.getUserStatus());
            entity.setBirthday(ConstantUtil.USER_BIRTHDAY);

            userRepository.save(entity);
        });
        var actual = userRepository.findById(ConstantUtil.USER_ID_5);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getUsername()),
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getFirstName()),
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getLastName()),
                        () -> assertEquals(ConstantUtil.USER_EMAIL_TEST_GMAIL_COM, entity.getEmail()),
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getPassword()),
                        () -> assertEquals(ConstantUtil.USER_ROLE_ID_2, entity.getUserRole().getId()),
                        () -> assertEquals(ConstantUtil.USER_STATUS_ID_2, entity.getUserStatus().getId()),
                        () -> assertEquals(ConstantUtil.USER_ID_5, entity.getId()),
                        () -> assertEquals(ConstantUtil.USER_BIRTHDAY, entity.getBirthday())
                )
        );
    }

    @Test
    @DisplayName("Delete user.")
    void deleteUser() {
        var expected = userRepository.count() - 1;

        userRepository.deleteById(ConstantUtil.USER_ID_4);
        var actual = userRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find all user by user role filter.")
    void findAllUserByUserRoleFilter() {
        var expected = 2;
        var userRole = new UserRole();
        userRole.setName(ConstantUtil.USER_ROLE_NAME_READER);

        var actual = userRepository.findAllByUserRoleFilter(
                userRoleFilterMapper.map(userRole)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all user by user status filter.")
    void findAllUserByUserStatusFilter() {
        var expected = 1;
        var userStatus = new UserStatus();
        userStatus.setName(ConstantUtil.USER_STATUS_NAME_GUEST);

        var actual = userRepository.findAllByUserStatusFilter(
                userStatusFilterMapper.map(userStatus)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all user by user filter.")
    void findAllUserByUserFilter() {
        var expected = 1;
        var user = new User();
        user.setFirstName(ConstantUtil.USER_FIRST_NAME_SVETA);

        var actual = userRepository.findAllByUserFilter(
                userFilterMapper.map(user)
        );

        assertThat(actual).hasSize(expected);
    }
}
