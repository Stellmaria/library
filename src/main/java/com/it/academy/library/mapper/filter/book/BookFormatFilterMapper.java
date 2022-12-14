package com.it.academy.library.mapper.filter.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.service.dto.filter.book.BookFormatFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookFormatFilterMapper implements Mapper<BookFormat, BookFormatFilter> {
    @Override
    public BookFormatFilter map(@NotNull BookFormat object) {
        return new BookFormatFilter(
                object.getId(),
                object.getName()
        );
    }
}
