package com.it.academy.library.mapper.convert.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.service.dto.read.book.BookFormatReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookFormatMapper implements Mapper<BookFormatReadDto, BookFormat> {
    @Override
    public BookFormat map(@NotNull BookFormatReadDto object) {
        var bookFormat = new BookFormat();

        bookFormat.setId(object.getId());
        bookFormat.setName(object.getName());

        return bookFormat;
    }
}
