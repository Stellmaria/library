package com.it.academy.library.model.repository.filter.impl.book;

import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.service.dto.filter.AuthorFilter;
import com.it.academy.library.service.dto.filter.book.BookAdditionalFilter;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import com.it.academy.library.service.dto.filter.book.BookFormatFilter;
import com.it.academy.library.service.dto.filter.book.BookGenreFilter;
import com.it.academy.library.service.dto.filter.book.BookLanguageFilter;
import com.it.academy.library.service.dto.filter.book.BookPublishingHouseFilter;
import com.it.academy.library.service.dto.filter.book.BookSeriesFilter;
import com.it.academy.library.service.dto.filter.book.BookStatusFilter;
import com.it.academy.library.service.dto.filter.order.OrderFilter;
import com.it.academy.library.service.dto.filter.user.UserFilter;

import java.util.Collection;

public interface FilterBookRepository {
    /**
     * Search for books by book filter.
     *
     * @param bookFilter filter.
     * @return books.
     */
    Collection<Book> findAllByBookFilter(BookFilter bookFilter);

    /**
     * Search for books by book additional information.
     *
     * @param bookAdditionalFilter filter.
     * @return books.
     */
    Collection<Book> findAllByBookAdditionalFilter(BookAdditionalFilter bookAdditionalFilter);

    /**
     * Search for books by author's filter.
     *
     * @param authorFilter filter.
     * @return books.
     */
    Collection<Book> findAllByAuthorFilter(AuthorFilter authorFilter);

    /**
     * Search for books by book genre filter.
     *
     * @param bookGenreFilter filter.
     * @return books.
     */
    Collection<Book> findAllByBookGenreFilter(BookGenreFilter bookGenreFilter);

    /**
     * Search for books by book language filter.
     *
     * @param bookLanguageFilter filter.
     * @return books.
     */
    Collection<Book> findAllByBookLanguageFilter(BookLanguageFilter bookLanguageFilter);

    /**
     * Search for books by book format filter.
     *
     * @param bookFormatFilter filter.
     * @return books.
     */
    Collection<Book> findAllByBookFormatFilter(BookFormatFilter bookFormatFilter);

    /**
     * Search for books by the filter of book publishing houses.
     *
     * @param bookPublishingHouseFilter filter.
     * @return books.
     */
    Collection<Book> findAllByBookPublishingHouseFilter(BookPublishingHouseFilter bookPublishingHouseFilter);

    /**
     * Search for books by book status filter.
     *
     * @param bookStatusFilter filter.
     * @return books.
     */
    Collection<Book> findAllByBookStatusFilter(BookStatusFilter bookStatusFilter);

    /**
     * Search for books by book series filter.
     *
     * @param bookSeriesFilter filter.
     * @return books.
     */
    Collection<Book> findAllByBookSeriesFilter(BookSeriesFilter bookSeriesFilter);

    /**
     * Search for books by order filter.
     *
     * @param orderFilter filter.
     * @return books.
     */
    Collection<Book> findAllByOrderFilter(OrderFilter orderFilter);

    /**
     * Search for books by user.
     *
     * @param userFilter filter.
     * @return books.
     */
    Collection<Book> findAllByUserFilter(UserFilter userFilter);
}
