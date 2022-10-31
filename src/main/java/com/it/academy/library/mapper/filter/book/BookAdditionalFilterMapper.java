package com.it.academy.library.mapper.filter.book;

import com.it.academy.library.dto.filter.book.BookAdditionalFilter;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookAdditional;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookAdditionalFilterMapper implements Mapper<BookAdditional, BookAdditionalFilter> {
    @Override
    public BookAdditionalFilter map(@NotNull BookAdditional object) {
        return new BookAdditionalFilter(
                object.getBook() != null ? object.getBook().getId() : null,
                object.getVolume(),
                object.getSerialNo(),
                object.getPrice(),
                object.getLink()
        );
    }
}
