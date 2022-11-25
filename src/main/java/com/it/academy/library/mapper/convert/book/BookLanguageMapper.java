package com.it.academy.library.mapper.convert.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookLanguage;
import com.it.academy.library.service.dto.read.book.BookLanguageReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookLanguageMapper implements Mapper<BookLanguageReadDto, BookLanguage> {
    @Override
    public BookLanguage map(@NotNull BookLanguageReadDto object) {
        return BookLanguage.builder()
                .id(object.getId())
                .name(object.getName())
                .build();
    }
}
