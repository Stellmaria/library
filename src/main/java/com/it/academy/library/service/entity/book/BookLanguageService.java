package com.it.academy.library.service.entity.book;

import com.it.academy.library.service.dto.read.book.BookLanguageReadDto;

import java.util.Collection;

public interface BookLanguageService {
    /**
     * Search for all book languages.
     *
     * @return collection of found book languages.
     */
    Collection<BookLanguageReadDto> findAll();
}
