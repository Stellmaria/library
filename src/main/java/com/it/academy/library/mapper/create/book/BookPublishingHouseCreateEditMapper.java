package com.it.academy.library.mapper.create.book;

import com.it.academy.library.dto.create.book.BookPublishingHouseCreateEditDto;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookPublishingHouseCreateEditMapper implements
        Mapper<BookPublishingHouseCreateEditDto, BookPublishingHouse> {
    @Override
    public BookPublishingHouse map(@NotNull BookPublishingHouseCreateEditDto object) {
        var bookPublishingHouse = new BookPublishingHouse();
        bookPublishingHouse.setName(object.getName());

        return bookPublishingHouse;
    }
}
