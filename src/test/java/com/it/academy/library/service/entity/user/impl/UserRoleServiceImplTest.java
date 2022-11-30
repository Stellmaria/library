package com.it.academy.library.service.entity.user.impl;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.model.repository.entity.user.UserRoleRepository;
import com.it.academy.library.service.entity.user.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@DisplayName("User role service test.")
class UserRoleServiceImplTest extends IntegrationTestBase {
    private final UserRoleRepository userRoleRepository;

    private final UserRoleService userRoleService;

    @Test
    @DisplayName("Find all user role.")
    void findAll() {
        var expected = userRoleRepository.count();

        var actual = userRoleService.findAll();

        assertThat(actual).hasSize(Math.toIntExact(expected));
    }
}
