package com.it.academy.library.model.repository.filter.book;

import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.service.dto.filter.book.BookStatusFilter;

import java.util.Collection;

public interface FilterBookStatusRepository {
    /**
     * Search for book statuses by book status filter.
     *
     * @param bookStatusFilter filter.
     * @return books statuses.
     */
    Collection<BookStatus> findAllByBookStatusFilter(BookStatusFilter bookStatusFilter);
}
