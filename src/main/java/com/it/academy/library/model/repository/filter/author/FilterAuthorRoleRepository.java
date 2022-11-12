package com.it.academy.library.model.repository.filter.author;

import com.it.academy.library.model.entity.author.AuthorRole;
import com.it.academy.library.service.dto.filter.author.AuthorRoleFilter;

import java.util.Collection;

public interface FilterAuthorRoleRepository {
    /**
     * Search for the author's role by the filter of the author's role.
     *
     * @param authorRoleFilter filter.
     * @return authors role.
     */
    Collection<AuthorRole> findAllByAuthorRoleFilter(AuthorRoleFilter authorRoleFilter);
}
