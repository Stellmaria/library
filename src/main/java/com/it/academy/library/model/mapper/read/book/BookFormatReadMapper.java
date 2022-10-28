package com.it.academy.library.model.mapper.read.book;

import com.it.academy.library.dto.read.book.BookFormatReadDto;
import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookFormatReadMapper implements Mapper<BookFormat, BookFormatReadDto> {
    @Override
    public BookFormatReadDto map(@NotNull BookFormat object) {
        return new BookFormatReadDto(
                object.getId(),
                object.getName()
        );
    }
}
