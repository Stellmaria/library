package com.it.academy.library.model.repository.filter.user;

import com.it.academy.library.dto.filter.user.UserFilter;
import com.it.academy.library.dto.filter.user.UserRoleFilter;
import com.it.academy.library.dto.filter.user.UserStatusFilter;
import com.it.academy.library.model.entity.user.User;

import java.util.Collection;

public interface FilterUserRepository {
    /**
     * Search for users by user filter.
     *
     * @param userFilter filter.
     * @return users.
     */
    Collection<User> findAllByUserFilter(UserFilter userFilter);

    /**
     * Search for users by user role filter.
     *
     * @param userRoleFilter filter.
     * @return users.
     */
    Collection<User> findAllByUserRoleFilter(UserRoleFilter userRoleFilter);

    /**
     * Search for users by user status filter.
     *
     * @param userStatusFilter filter.
     * @return users.
     */
    Collection<User> findAllByUserStatusFilter(UserStatusFilter userStatusFilter);
}
