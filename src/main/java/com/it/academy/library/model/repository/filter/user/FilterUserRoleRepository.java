package com.it.academy.library.model.repository.filter.user;


import com.it.academy.library.dto.filter.user.UserRoleFilter;
import com.it.academy.library.model.entity.user.UserRole;

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
