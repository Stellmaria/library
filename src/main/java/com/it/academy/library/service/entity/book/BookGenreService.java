package com.it.academy.library.service.entity.book;

import com.it.academy.library.service.dto.create.book.BookGenreCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookGenreFilter;
import com.it.academy.library.service.dto.read.book.BookGenreReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface BookGenreService {
    /**
     * Creation of a new book genre.
     *
     * @param dto for creating.
     * @return new book genre.
     */
    BookGenreReadDto create(BookGenreCreateEditDto dto);

    /**
     * Search book genre by book genre id.
     *
     * @param id for search.
     * @return book genre.
     */
    Optional<BookGenreReadDto> findById(Integer id);

    /**
     * Search for all book genres.
     *
     * @return a collection of all found book genres.
     */
    Collection<BookGenreReadDto> findAll();

    /**
     * Search all book genres with filtering.
     *
     * @param filter   for search.
     * @param pageable for search.
     * @return a collection of all found book genres.
     */
    Page<BookGenreReadDto> findAll(BookGenreFilter filter, Pageable pageable);

    /**
     * Search all book genres by book id .
     *
     * @param id for search.
     * @return a collection of all found book genres.
     */
    Collection<BookGenreReadDto> findAllByBookId(Long id);

    /**
     * Book genre update by book genre id.
     *
     * @param id  for an update.
     * @param dto for an update.
     * @return updated book genre.
     */
    Optional<BookGenreReadDto> update(Integer id, BookGenreCreateEditDto dto);

    /**
     * Delete book genre by book genre id.
     *
     * @param id for removing.
     * @return true - succeeded; false - failed.
     */
    boolean delete(Integer id);
}
