package com.it.academy.library.service.entity.book;

import com.it.academy.library.service.dto.read.book.BookStatusReadDto;

import java.util.Collection;

public interface BookStatusService {
    /**
     * Search for all book statuses.
     *
     * @return collection of found book statuses.
     */
    Collection<BookStatusReadDto> findAll();
}
