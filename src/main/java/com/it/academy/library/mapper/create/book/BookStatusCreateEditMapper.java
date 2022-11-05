package com.it.academy.library.mapper.create.book;

import com.it.academy.library.dto.create.book.BookStatusCreateEditDto;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookStatus;
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
