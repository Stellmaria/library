package com.it.academy.library.model.repository.entity.author;

import com.it.academy.library.model.entity.author.AuthorRole;
import com.it.academy.library.model.repository.filter.author.FilterAuthorRoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AuthorRoleRepository extends
        JpaRepository<AuthorRole, Integer>,
        FilterAuthorRoleRepository,
        QuerydslPredicateExecutor<AuthorRole> {
}
