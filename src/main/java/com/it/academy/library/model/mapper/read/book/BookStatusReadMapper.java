package com.it.academy.library.model.mapper.read.book;

import com.it.academy.library.dto.read.book.BookStatusReadDto;
import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookStatusReadMapper implements Mapper<BookStatus, BookStatusReadDto> {
    @Override
    public BookStatusReadDto map(@NotNull BookStatus object) {
        return new BookStatusReadDto(
                object.getId(),
                object.getName()
        );
    }
}
