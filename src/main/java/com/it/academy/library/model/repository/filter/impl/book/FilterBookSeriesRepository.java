package com.it.academy.library.model.repository.filter.impl.book;

import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.service.dto.filter.book.BookSeriesFilter;

import java.util.Collection;

public interface FilterBookSeriesRepository {
    /**
     * Search for book series by book series filter.
     *
     * @param bookSeriesFilter filter.
     * @return books series.
     */
    Collection<BookSeries> findAllByBookSeriesFilter(BookSeriesFilter bookSeriesFilter);
}
