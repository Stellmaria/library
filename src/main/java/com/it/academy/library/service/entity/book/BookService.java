package com.it.academy.library.service.entity.book;

import com.it.academy.library.service.dto.create.book.BookCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import com.it.academy.library.service.dto.read.book.BookReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface BookService {
    /**
     * Create a new book.
     *
     * @param dto for creating.
     * @return new created book.
     */
    BookReadDto create(BookCreateEditDto dto);

    /**
     * Search all books.
     *
     * @return collection of found books.
     */
    Collection<BookReadDto> findAll();

    /**
     * Search for all books with filtering.
     *
     * @param filter   for search.
     * @param pageable for search.
     * @return collection of found books.
     */
    Page<BookReadDto> findAll(BookFilter filter, Pageable pageable);

    /**
     * Search all books with by book series id.
     *
     * @param id for search.
     * @return collection of found books.
     */
    Collection<BookReadDto> findAllBySeriesId(Integer id);

    Collection<BookReadDto> findAllByBookPublishingHouseId(Integer id);

    /**
     * Search for all books by author id.
     *
     * @param id for search.
     * @return collection of found books.
     */
    Collection<BookReadDto> findAllByAuthorId(Long id);

    /**
     * Book search by book id.
     *
     * @param id for search.
     * @return book.
     */
    Optional<BookReadDto> findById(Long id);

    /**
     * Book update by book id.
     *
     * @param id  for an update.
     * @param dto for an update.
     * @return updated book.
     */
    Optional<BookReadDto> update(Long id, BookCreateEditDto dto);

    /**
     * Deleting a book by book id.
     *
     * @param id for removing.
     * @return true - succeeded; false - failed.
     */
    boolean delete(Long id);

    /**
     * Book cover search by book id.
     *
     * @param id for search.
     * @return book cover.
     */
    Optional<byte[]> findImage(Long id);
}
