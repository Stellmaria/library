package com.it.academy.library.model.repository.filter.user.impl;

import com.it.academy.library.dto.filter.user.UserRoleFilter;
import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.model.repository.filter.user.FilterUserRoleRepository;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.user.QUserRole.userRole;

@RequiredArgsConstructor
public class FilterUserRoleRepositoryImpl implements FilterUserRoleRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<UserRole> findAllByUserRoleFilter(@NotNull UserRoleFilter userRoleFilter) {
        return new JPAQuery<UserRole>(entityManager)
                .select(userRole)
                .from(userRole)
                .where(UserRoleFilter.queryPredicates(userRoleFilter))
                .fetch();
    }
}
