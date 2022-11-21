package com.it.academy.library.service.entity.book;

import com.it.academy.library.service.dto.read.book.BookFormatReadDto;

import java.util.Collection;

public interface BookFormatService {
    /**
     * Search for all book formats.
     *
     * @return collection of found book formats.
     */
    Collection<BookFormatReadDto> findAll();
}
