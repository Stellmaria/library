package com.it.academy.library.model.mapper.create.book;

import com.it.academy.library.dto.create.book.BookStatusCreateEditDto;
import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookStatusCreateEditMapper implements Mapper<BookStatusCreateEditDto, BookStatus> {
    @Override
    public BookStatus map(@NotNull BookStatusCreateEditDto object) {
        var bookStatus = new BookStatus();
        bookStatus.setName(object.getName());
        return bookStatus;
    }
}
