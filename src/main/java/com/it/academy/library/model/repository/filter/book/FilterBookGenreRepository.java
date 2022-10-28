package com.it.academy.library.model.repository.filter.book;

import com.it.academy.library.dto.filter.book.BookFilter;
import com.it.academy.library.dto.filter.book.BookGenreFilter;
import com.it.academy.library.model.entity.book.BookGenre;

import java.util.Collection;

public interface FilterBookGenreRepository {
    /**
     * Search for book genres of books by filtering book genres.
     *
     * @param bookGenreFilter filter.
     * @return book genres.
     */
    Collection<BookGenre> findAllByBookGenreFilter(BookGenreFilter bookGenreFilter);

    /**
     * Search for book genres of books by book filter.
     *
     * @param bookFilter for filter.
     * @return book genres.
     */
    Collection<BookGenre> findAllByBookFilter(BookFilter bookFilter);
}
