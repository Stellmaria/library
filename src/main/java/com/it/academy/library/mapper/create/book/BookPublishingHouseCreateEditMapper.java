package com.it.academy.library.mapper.create.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.service.dto.create.book.BookPublishingHouseCreateEditDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookPublishingHouseCreateEditMapper
        implements Mapper<BookPublishingHouseCreateEditDto, BookPublishingHouse> {
    @Override
    public BookPublishingHouse map(@NotNull BookPublishingHouseCreateEditDto fromObject,
                                   @NotNull BookPublishingHouse toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    @Override
    public BookPublishingHouse map(@NotNull BookPublishingHouseCreateEditDto object) {
        var bookPublishingHouse = new BookPublishingHouse();

        copy(object, bookPublishingHouse);

        return bookPublishingHouse;
    }

    private void copy(@NotNull BookPublishingHouseCreateEditDto object,
                      @NotNull BookPublishingHouse bookPublishingHouse) {
        bookPublishingHouse.setName(object.getName());
    }
}
