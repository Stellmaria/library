package com.it.academy.library.mapper.convert.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.service.dto.read.book.BookStatusReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookStatusMapper implements Mapper<BookStatusReadDto, BookStatus> {
    @Override
    public BookStatus map(@NotNull BookStatusReadDto object) {
        var bookStatus = new BookStatus();

        bookStatus.setId(object.getId());
        bookStatus.setName(object.getName());

        return bookStatus;
    }
}
