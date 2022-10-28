package com.it.academy.library.model.repository.filter.book;

import com.it.academy.library.dto.filter.book.BookPublishingHouseFilter;
import com.it.academy.library.model.entity.book.BookPublishingHouse;

import java.util.Collection;

public interface FilterBookPublishingHouseRepository {
    /**
     * Search for book publishing houses by book publishing houses filter.
     *
     * @param bookPublishingHouseFilter filter.
     * @return book publishing houses.
     */
    Collection<BookPublishingHouse> findAllByBookPublishingHouseFilter(
            BookPublishingHouseFilter bookPublishingHouseFilter);
}
