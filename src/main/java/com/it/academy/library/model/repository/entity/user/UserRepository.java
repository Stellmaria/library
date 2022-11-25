package com.it.academy.library.model.repository.entity.user;

import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.repository.filter.impl.user.FilterUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        QuerydslPredicateExecutor<User> {
}
