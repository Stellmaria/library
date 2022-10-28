package com.it.academy.library.model.repository.filter.book;

import com.it.academy.library.dto.filter.book.BookFormatFilter;
import com.it.academy.library.model.entity.book.BookFormat;

import java.util.Collection;

public interface FilterBookFormatRepository {
    /**
     * Search book formats and book format filter.
     *
     * @param bookFormatFilter filter.
     * @return books format.
     */
    Collection<BookFormat> findAllByBookFormatFilter(BookFormatFilter bookFormatFilter);
}
