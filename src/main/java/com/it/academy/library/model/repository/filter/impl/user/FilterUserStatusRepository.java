package com.it.academy.library.model.repository.filter.impl.user;


import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.service.dto.filter.user.UserStatusFilter;

import java.util.Collection;

public interface FilterUserStatusRepository {
    /**
     * Search for user statuses by user status filter.
     *
     * @param userStatusFilter filter.
     * @return users status.
     */
    Collection<UserStatus> findAllByUserStatusFilter(UserStatusFilter userStatusFilter);
}
