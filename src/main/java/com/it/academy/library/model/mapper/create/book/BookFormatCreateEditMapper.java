package com.it.academy.library.model.mapper.create.book;

import com.it.academy.library.dto.create.book.BookFormatCreateEditDto;
import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.model.mapper.Mapper;
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
