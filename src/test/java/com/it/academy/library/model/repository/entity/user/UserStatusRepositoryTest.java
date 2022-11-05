package com.it.academy.library.model.repository.entity.user;

import com.it.academy.library.mapper.filter.user.UserStatusFilterMapper;
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
@DisplayName("User status repository test.")
class UserStatusRepositoryTest extends IntegrationTestBase {
    private static final Integer USER_STATUS_ID_3 = 3;
    private static final Integer USER_STATUS_ID_4 = 4;

    private final UserStatusRepository userStatusRepository;
    private final UserStatusFilterMapper userStatusFilterMapper;

    @Test
    @DisplayName("Save user status.")
    void saveUserStatus() {
        var expectedCount = userStatusRepository.count() + 1;

        var actual = userStatusRepository.save(
                UserStatus.builder()
                        .name(ConstantUtil.NEW + ConstantUtil.SAVE)
                        .build()
        );
        var actualCount = userStatusRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getName())
        );
    }

    @Test
    @DisplayName("Update user status.")
    void updateUserStatus() {
        var userStatus = userStatusRepository.findById(USER_STATUS_ID_3);

        userStatus.ifPresent(it -> {
            it.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            userStatusRepository.save(it);
        });
        var actual = userStatusRepository.findById(USER_STATUS_ID_3);

        actual.ifPresent(it -> {
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getName());
            assertEquals(USER_STATUS_ID_3, it.getId());
        });
    }

    @Test
    @DisplayName("Delete user status.")
    void delete() {
        var expected = userStatusRepository.count() - 1;

        userStatusRepository.deleteById(USER_STATUS_ID_4);
        var actual = userStatusRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find all user status by user status filter.")
    void findAllByUserStatusFilter() {
        var actual = userStatusRepository.findAllByUserStatusFilter(userStatusFilterMapper.map(
                UserStatus.builder()
                        .name(ConstantUtil.USER_STATUS_NAME_GUEST)
                        .build())
        );

        assertThat(actual).hasSize(1);
    }
}