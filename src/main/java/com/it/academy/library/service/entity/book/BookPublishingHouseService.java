package com.it.academy.library.service.entity.book;

import com.it.academy.library.service.dto.read.book.BookPublishingHouseReadDto;

import java.util.Collection;

public interface BookPublishingHouseService {
    /**
     * Search for all book publishers.
     *
     * @return Collection of found book publishers.
     */
    Collection<BookPublishingHouseReadDto> findAll();
}