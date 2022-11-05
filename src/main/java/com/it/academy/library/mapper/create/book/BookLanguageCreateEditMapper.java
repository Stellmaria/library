package com.it.academy.library.mapper.create.book;

import com.it.academy.library.dto.create.book.BookLanguageCreateEditDto;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookLanguage;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookLanguageCreateEditMapper implements Mapper<BookLanguageCreateEditDto, BookLanguage> {
    @Override
    public BookLanguage map(@NotNull BookLanguageCreateEditDto object) {
        var bookLanguage = new BookLanguage();
        bookLanguage.setName(object.getName());

        return bookLanguage;
    }
}
