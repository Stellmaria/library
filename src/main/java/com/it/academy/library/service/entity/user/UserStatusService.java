package com.it.academy.library.service.entity.user;

import com.it.academy.library.service.dto.read.user.UserStatusReadDto;

import java.util.Collection;

public interface UserStatusService {
    /**
     * Search for all user statuses.
     *
     * @return collection of found user statuses.
     */
    Collection<UserStatusReadDto> findAll();
}
