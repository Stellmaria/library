package com.it.academy.library.service.entity.user;

import com.it.academy.library.service.dto.read.user.UserRoleReadDto;

import java.util.Collection;

public interface UserRoleService {
    /**
     * Search for all user roles.
     *
     * @return collection of found user roles.
     */
    Collection<UserRoleReadDto> findAll();
}
