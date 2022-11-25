package com.it.academy.library.service.entity.book;

import com.it.academy.library.service.dto.create.book.BookPublishingHouseCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookPublishingHouseFilter;
import com.it.academy.library.service.dto.read.book.BookPublishingHouseReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface BookPublishingHouseService {
    /**
     * Search for all book publishers.
     *
     * @return collection of found book publishers.
     */
    Collection<BookPublishingHouseReadDto> findAll();

    /**
     * Creation of a new book publishing house.
     *
     * @param dto for creating.
     * @return new book publishing house.
     */
    BookPublishingHouseReadDto create(BookPublishingHouseCreateEditDto dto);

    /**
     * Search for a book publisher by book publisher id.
     *
     * @param id for search.
     * @return book publishing house.
     */
    Optional<BookPublishingHouseReadDto> findById(Integer id);

    /**
     * Search for a book publisher by book publisher ID with filtering.
     *
     * @param filter   for search.
     * @param pageable for search.
     * @return collection of found book publishers.
     */
    Page<BookPublishingHouseReadDto> findAll(BookPublishingHouseFilter filter, Pageable pageable);

    /**
     * Search for a book publisher by book publisher name.
     *
     * @param name for search.
     * @return book publishing house.
     */
    Optional<BookPublishingHouseReadDto> findByName(String name);

    /**
     * Book publisher update by book publishing house id.
     *
     * @param id  for an update.
     * @param dto for an update.
     * @return updated book publishing house.
     */
    Optional<BookPublishingHouseReadDto> update(Integer id, BookPublishingHouseCreateEditDto dto);

    /**
     * Removing a book publisher by book publisher id.
     *
     * @param id for removing.
     * @return true - succeeded; false - failed.
     */
    boolean delete(Integer id);
}