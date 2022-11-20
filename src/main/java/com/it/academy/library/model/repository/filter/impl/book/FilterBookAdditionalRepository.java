package com.it.academy.library.model.repository.filter.impl.book;

import com.it.academy.library.model.entity.book.BookAdditional;
import com.it.academy.library.service.dto.filter.book.BookAdditionalFilter;

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
