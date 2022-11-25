package com.it.academy.library.model.repository.entity.user;

import com.it.academy.library.mapper.filter.user.UserRoleFilterMapper;
import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("User role repository test.")
class UserRoleRepositoryTest extends IntegrationTestBase {
    private static final Integer USER_ROLE_ID_4 = 4;
    private static final Integer USER_ROLE_ID_5 = 5;

    private final UserRoleRepository userRoleRepository;

    private final UserRoleFilterMapper userRoleFilterMapper;

    @Test
    @DisplayName("Save user role.")
    void saveUserRole() {
        var expectedCount = userRoleRepository.count() + 1;
        var userRole = UserRole.builder()
                .name(ConstantUtil.NEW + ConstantUtil.SAVE)
                .build();

        var actual = userRoleRepository.save(userRole);
        var actualCount = userRoleRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getName())
        );
    }

    @Test
    @DisplayName("Update user role.")
    void updateUserRole() {
        var userRole = userRoleRepository.findById(USER_ROLE_ID_4);

        userRole.ifPresent(it -> {
            it.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            userRoleRepository.save(it);
        });
        var actual = userRoleRepository.findById(USER_ROLE_ID_4);

        actual.ifPresent(it -> {
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getName());
            assertEquals(USER_ROLE_ID_4, it.getId());
        });
    }

    @Test
    @DisplayName("Delete user role.")
    void deleteUserRole() {
        var expected = userRoleRepository.count() - 1;

        userRoleRepository.deleteById(USER_ROLE_ID_5);
        var actual = userRoleRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find user role by user role filter.")
    void findAllUserRoleByUserRoleFilter() {
        var userRole = UserRole.builder()
                .name(ConstantUtil.USER_ROLE_NAME_READER)
                .build();

        var actual = userRoleRepository.findAllByUserRoleFilter(userRoleFilterMapper.map(userRole));

        assertThat(actual).hasSize(1);
    }
}
