package com.it.academy.library.model.repository.filter.book;

import com.it.academy.library.dto.filter.book.BookSeriesFilter;
import com.it.academy.library.model.entity.book.BookSeries;

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
