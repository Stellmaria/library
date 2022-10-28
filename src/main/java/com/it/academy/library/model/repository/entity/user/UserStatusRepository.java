package com.it.academy.library.model.repository.entity.user;

import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.model.repository.filter.user.FilterUserStatusRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserStatusRepository extends
        JpaRepository<UserStatus, Integer>,
        FilterUserStatusRepository,
        QuerydslPredicateExecutor<UserStatus> {
}
