package com.it.academy.library.model.repository.filter.book;

import com.it.academy.library.dto.filter.book.BookAdditionalFilter;
import com.it.academy.library.model.entity.book.BookAdditional;

import java.util.Collection;

public interface FilterBookAdditionalRepository {
    /**
     * Search for book additional information by book additional information filter.
     *
     * @param bookAdditionalFilter filter.
     * @return book additional.
     */
    Collection<BookAdditional> findAllByBookAdditionalFilter(BookAdditionalFilter bookAdditionalFilter);
}
