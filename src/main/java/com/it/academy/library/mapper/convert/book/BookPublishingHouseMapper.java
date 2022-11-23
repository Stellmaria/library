package com.it.academy.library.mapper.convert.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.service.dto.read.book.BookPublishingHouseReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookPublishingHouseMapper implements Mapper<BookPublishingHouseReadDto, BookPublishingHouse> {
    @Override
    public BookPublishingHouse map(@NotNull BookPublishingHouseReadDto object) {
        var bookPublishingHouse = new BookPublishingHouse();

        bookPublishingHouse.setId(object.getId());
        bookPublishingHouse.setName(object.getName());

        return bookPublishingHouse;
    }
}
