package com.it.academy.library.model.repository.filter.book;

import com.it.academy.library.dto.filter.book.BookLanguageFilter;
import com.it.academy.library.model.entity.book.BookLanguage;

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
