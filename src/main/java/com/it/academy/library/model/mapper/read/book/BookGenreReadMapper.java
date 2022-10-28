package com.it.academy.library.model.mapper.read.book;

import com.it.academy.library.dto.read.book.BookGenreReadDto;
import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookGenreReadMapper implements Mapper<BookGenre, BookGenreReadDto> {
    @Override
    public BookGenreReadDto map(@NotNull BookGenre object) {
        return new BookGenreReadDto(
                object.getId(),
                object.getName(),
                object.getDescription()
        );
    }
}
