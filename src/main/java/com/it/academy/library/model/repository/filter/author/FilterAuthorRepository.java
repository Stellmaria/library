package com.it.academy.library.model.repository.filter.author;

import com.it.academy.library.model.entity.author.Author;
import com.it.academy.library.service.dto.filter.author.AuthorFilter;
import com.it.academy.library.service.dto.filter.author.AuthorRoleFilter;
import com.it.academy.library.service.dto.filter.book.BookFilter;

import java.util.Collection;

public interface FilterAuthorRepository {
    /**
     * Search for authors by author's filter.
     *
     * @param authorFilter filter.
     * @return authors.
     */
    Collection<Author> findAllByAuthorFilter(AuthorFilter authorFilter);

    /**
     * Search for authors by book filter.
     *
     * @param bookFilter filter.
     * @return authors.
     */
    Collection<Author> findAllByBookFilter(BookFilter bookFilter);

    /**
     * Search for authors by author role filter.
     *
     * @param authorRoleFilter filter.
     * @return authors.
     */
    Collection<Author> findAllByAuthorRoleFilter(AuthorRoleFilter authorRoleFilter);
}
