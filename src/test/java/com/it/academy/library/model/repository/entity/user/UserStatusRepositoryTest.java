package com.it.academy.library.model.repository.entity.user;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.mapper.filter.user.UserStatusFilterMapper;
import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("User status repository test.")
class UserStatusRepositoryTest extends IntegrationTestBase {
    private final UserStatusRepository userStatusRepository;

    private final UserStatusFilterMapper userStatusFilterMapper;

    private final UserStatus userStatus = new UserStatus();

    @Test
    @DisplayName("Save user status.")
    void saveUserStatus() {
        var expectedCount = userStatusRepository.count() + 1;
        userStatus.setName(ConstantUtil.NEW + ConstantUtil.SAVE);

        var actual = userStatusRepository.save(userStatus);
        var actualCount = userStatusRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount, "The number of user statuses must match."),
                () -> assertEquals(userStatus.getName(), actual.getName())
        );
    }

    @Test
    @DisplayName("Update user status.")
    void updateUserStatus() {
        var userStatus = userStatusRepository.findById(ConstantUtil.USER_STATUS_ID_3);

        userStatus.ifPresent(entity -> {
            entity.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);

            userStatusRepository.save(entity);
        });
        var actual = userStatusRepository.findById(ConstantUtil.USER_STATUS_ID_3);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getName()),
                        () -> assertEquals(ConstantUtil.USER_STATUS_ID_3, entity.getId(), "The ids must match.")
                )
        );
    }

    @Test
    @DisplayName("Delete user status.")
    void delete() {
        var expected = userStatusRepository.count() - 1;

        userStatusRepository.deleteById(ConstantUtil.USER_STATUS_ID_4);
        var actual = userStatusRepository.count();

        assertEquals(expected, actual, "The number of user statuses must match.");
    }

    @Test
    @DisplayName("Find all user status by user status filter.")
    void findAllByUserStatusFilter() {
        var expected = 1;
        userStatus.setName(ConstantUtil.USER_STATUS_NAME_GUEST);

        var actual = userStatusRepository.findAllByUserStatusFilter(
                userStatusFilterMapper.map(userStatus)
        );

        assertThat(actual).hasSize(expected);
    }
}
