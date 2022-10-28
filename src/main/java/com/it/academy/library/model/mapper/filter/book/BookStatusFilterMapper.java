package com.it.academy.library.model.mapper.filter.book;

import com.it.academy.library.dto.filter.book.BookStatusFilter;
import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookStatusFilterMapper implements Mapper<BookStatus, BookStatusFilter> {
    @Override
    public BookStatusFilter map(@NotNull BookStatus object) {
        return new BookStatusFilter(object.getName());
    }
}
