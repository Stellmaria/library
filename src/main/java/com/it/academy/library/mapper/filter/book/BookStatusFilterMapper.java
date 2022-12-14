package com.it.academy.library.mapper.filter.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.service.dto.filter.book.BookStatusFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookStatusFilterMapper implements Mapper<BookStatus, BookStatusFilter> {
    @Override
    public BookStatusFilter map(@NotNull BookStatus object) {
        return new BookStatusFilter(
                object.getId(),
                object.getName()
        );
    }
}
