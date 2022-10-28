package com.it.academy.library.model.repository.entity.user;

import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.model.repository.filter.user.FilterUserRoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRoleRepository extends
        JpaRepository<UserRole, Integer>,
        FilterUserRoleRepository,
        QuerydslPredicateExecutor<UserRole> {
}
