package com.it.academy.library.model.repository.entity.user;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.mapper.filter.user.UserRoleFilterMapper;
import com.it.academy.library.model.entity.user.UserRole;
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
    private final UserRoleRepository userRoleRepository;

    private final UserRoleFilterMapper userRoleFilterMapper;

    private final UserRole userRole = new UserRole();

    @Test
    @DisplayName("Save user role.")
    void saveUserRole() {
        var expectedCount = userRoleRepository.count() + 1;
        userRole.setName(ConstantUtil.NEW + ConstantUtil.SAVE);

        var actual = userRoleRepository.save(userRole);
        var actualCount = userRoleRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(userRole.getName(), actual.getName())
        );
    }

    @Test
    @DisplayName("Update user role.")
    void updateUserRole() {
        var userRole = userRoleRepository.findById(ConstantUtil.USER_ROLE_ID_4);

        userRole.ifPresent(entity -> {
            entity.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);

            userRoleRepository.save(entity);
        });
        var actual = userRoleRepository.findById(ConstantUtil.USER_ROLE_ID_4);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getName()),
                        () -> assertEquals(ConstantUtil.USER_ROLE_ID_4, entity.getId())
                )
        );
    }

    @Test
    @DisplayName("Delete user role.")
    void deleteUserRole() {
        var expected = userRoleRepository.count() - 1;

        userRoleRepository.deleteById(ConstantUtil.USER_ROLE_ID_2);
        var actual = userRoleRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find user role by user role filter.")
    void findAllUserRoleByUserRoleFilter() {
        var expected = 1;
        userRole.setName(ConstantUtil.USER_ROLE_NAME_READER);

        var actual = userRoleRepository.findAllByUserRoleFilter(
                userRoleFilterMapper.map(userRole)
        );

        assertThat(actual).hasSize(expected);
    }
}
