package com.it.academy.library.service.entity.user.impl;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.model.repository.entity.user.UserStatusRepository;
import com.it.academy.library.service.entity.user.UserStatusService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@DisplayName("User status service test.")
class UserStatusServiceImplTest extends IntegrationTestBase {
    private final UserStatusRepository userStatusRepository;

    private final UserStatusService userStatusService;

    @Test
    @DisplayName("Find all user status.")
    void findAll() {
        var expected = userStatusRepository.count();

        var actual = userStatusService.findAll();

        assertThat(actual).hasSize(Math.toIntExact(expected));
    }
}
