package com.it.academy.library.mapper.filter.book;

import com.it.academy.library.dto.filter.book.BookPublishingHouseFilter;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookPublishingHouseFilterMapper implements Mapper<BookPublishingHouse, BookPublishingHouseFilter> {
    @Override
    public BookPublishingHouseFilter map(@NotNull BookPublishingHouse object) {
        return new BookPublishingHouseFilter(
                object.getId(),
                object.getName()
        );
    }
}
