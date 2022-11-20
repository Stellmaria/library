package com.it.academy.library.model.repository.filter.impl.book;

import com.it.academy.library.model.entity.book.BookLanguage;
import com.it.academy.library.service.dto.filter.book.BookLanguageFilter;

import java.util.Collection;

public interface FilterBookLanguageRepository {
    /**
     * Search for book languages by book language filter.
     *
     * @param bookLanguageFilter filter.
     * @return book languages.
     */
    Collection<BookLanguage> findAllByBookLanguageFilter(BookLanguageFilter bookLanguageFilter);
}
