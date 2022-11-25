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
        return BookStatus.builder()
                .id(object.getId())
                .name(object.getName())
                .build();
    }
}
