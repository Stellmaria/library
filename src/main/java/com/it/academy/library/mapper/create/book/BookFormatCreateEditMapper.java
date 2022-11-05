package com.it.academy.library.mapper.create.book;

import com.it.academy.library.dto.create.book.BookFormatCreateEditDto;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookFormat;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookFormatCreateEditMapper implements Mapper<BookFormatCreateEditDto, BookFormat> {
    @Override
    public BookFormat map(@NotNull BookFormatCreateEditDto object) {
        var bookFormat = new BookFormat();
        bookFormat.setName(object.getName());

        return bookFormat;
    }
}
