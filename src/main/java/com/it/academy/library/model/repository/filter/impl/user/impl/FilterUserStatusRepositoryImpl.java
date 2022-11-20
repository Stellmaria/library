package com.it.academy.library.model.repository.filter.impl.user.impl;

import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.model.repository.filter.impl.user.FilterUserStatusRepository;
import com.it.academy.library.service.dto.filter.user.UserStatusFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.user.QUserStatus.userStatus;

@RequiredArgsConstructor
public class FilterUserStatusRepositoryImpl implements FilterUserStatusRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<UserStatus> findAllByUserStatusFilter(@NotNull UserStatusFilter userStatusFilter) {
        return new JPAQuery<UserStatus>(entityManager)
                .select(userStatus)
                .from(userStatus)
                .where(UserStatusFilter.queryPredicates(userStatusFilter))
                .fetch();
    }
}
