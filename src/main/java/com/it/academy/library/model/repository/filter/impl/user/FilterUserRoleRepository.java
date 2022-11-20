package com.it.academy.library.model.repository.filter.impl.user;


import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.service.dto.filter.user.UserRoleFilter;

import java.util.Collection;

public interface FilterUserRoleRepository {
    /**
     * Search for user roles by user role filter.
     *
     * @param userRoleFilter filter.
     * @return user roles.
     */
    Collection<UserRole> findAllByUserRoleFilter(UserRoleFilter userRoleFilter);
}
