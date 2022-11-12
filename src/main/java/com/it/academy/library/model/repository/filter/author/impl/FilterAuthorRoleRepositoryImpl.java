package com.it.academy.library.model.repository.filter.author.impl;

import com.it.academy.library.model.entity.author.Author;
import com.it.academy.library.model.entity.author.AuthorRole;
import com.it.academy.library.model.repository.filter.author.FilterAuthorRoleRepository;
import com.it.academy.library.service.dto.filter.author.AuthorRoleFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.author.QAuthorRole.authorRole;

@RequiredArgsConstructor
public class FilterAuthorRoleRepositoryImpl implements FilterAuthorRoleRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<AuthorRole> findAllByAuthorRoleFilter(@NotNull AuthorRoleFilter authorRoleFilter) {
        return new JPAQuery<Author>(entityManager)
                .select(authorRole)
                .from(authorRole)
                .where(AuthorRoleFilter.queryPredicates(authorRoleFilter))
                .fetch();
    }
}
