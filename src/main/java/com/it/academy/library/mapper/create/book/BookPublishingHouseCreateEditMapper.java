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
        return create(fromObject);
    }

    @Override
    public BookPublishingHouse map(@NotNull BookPublishingHouseCreateEditDto object) {
        return create(object);
    }

    private BookPublishingHouse create(@NotNull BookPublishingHouseCreateEditDto object) {
        return BookPublishingHouse.builder()
                .name(object.getName())
                .build();
    }
}
