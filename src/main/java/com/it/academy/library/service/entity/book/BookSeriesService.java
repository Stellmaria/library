package com.it.academy.library.service.entity.book;

import com.it.academy.library.service.dto.create.book.BookSeriesCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookSeriesFilter;
import com.it.academy.library.service.dto.read.book.BookSeriesReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface BookSeriesService {
    /**
     * Creation of a new book series.
     *
     * @param dto for creating.
     * @return new series of books.
     */
    BookSeriesReadDto create(BookSeriesCreateEditDto dto);

    /**
     * Search for a book series by book series id.
     *
     * @param id for search.
     * @return book series.
     */
    Optional<BookSeriesReadDto> findById(Integer id);

    /**
     * Search for all series of books.
     *
     * @return collection of found book series.
     */
    Collection<BookSeriesReadDto> findAll();

    /**
     * Search for all series of books with filtering.
     *
     * @param filter   for search.
     * @param pageable for search.
     * @return collection of found book series.
     */
    Page<BookSeriesReadDto> findAll(BookSeriesFilter filter, Pageable pageable);

    /**
     * Book series update by book series id.
     *
     * @param id  for an update.
     * @param dto for an update.
     * @return updated book series.
     */
    Optional<BookSeriesReadDto> update(Integer id, BookSeriesCreateEditDto dto);

    /**
     * Deleting a book series by book series id.
     *
     * @param id for removing.
     * @return true - succeeded; false - failed.
     */
    boolean delete(Integer id);
}
