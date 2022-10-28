package com.it.academy.library.model.mapper.read.book;

import com.it.academy.library.dto.read.book.BookLanguageReadDto;
import com.it.academy.library.model.entity.book.BookLanguage;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookLanguageReadMapper implements Mapper<BookLanguage, BookLanguageReadDto> {
    @Override
    public BookLanguageReadDto map(@NotNull BookLanguage object) {
        return new BookLanguageReadDto(
                object.getId(),
                object.getName()
        );
    }
}
