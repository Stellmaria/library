package com.it.academy.library.mapper.read.book;

import com.it.academy.library.dto.read.book.BookPublishingHouseReadDto;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookPublishingHouseReadMapper implements Mapper<BookPublishingHouse, BookPublishingHouseReadDto> {
    @Override
    public BookPublishingHouseReadDto map(@NotNull BookPublishingHouse object) {
        return new BookPublishingHouseReadDto(
                object.getId(),
                object.getName()
        );
    }
}
