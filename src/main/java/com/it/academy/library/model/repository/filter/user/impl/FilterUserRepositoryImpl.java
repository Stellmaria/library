package com.it.academy.library.model.repository.filter.user.impl;

import com.it.academy.library.dto.filter.user.UserFilter;
import com.it.academy.library.dto.filter.user.UserRoleFilter;
import com.it.academy.library.dto.filter.user.UserStatusFilter;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.repository.filter.user.FilterUserRepository;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.user.QUser.user;


@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<User> findAllByUserFilter(@NotNull UserFilter userFilter) {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(UserFilter.queryPredicates(userFilter))
                .fetch();
    }

    @Override
    public Collection<User> findAllByUserRoleFilter(@NotNull UserRoleFilter userRoleFilter) {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(UserRoleFilter.queryPredicates(userRoleFilter))
                .fetch();
    }

    @Override
    public Collection<User> findAllByUserStatusFilter(@NotNull UserStatusFilter userStatusFilter) {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(UserStatusFilter.queryPredicates(userStatusFilter))
                .fetch();
    }
}
