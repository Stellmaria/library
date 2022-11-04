package com.it.academy.library.mapper.filter.book;

import com.it.academy.library.dto.filter.book.BookLanguageFilter;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookLanguage;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookLanguageFilterMapper implements Mapper<BookLanguage, BookLanguageFilter> {
    @Override
    public BookLanguageFilter map(@NotNull BookLanguage object) {
        return new BookLanguageFilter(
                object.getId(),
                object.getName()
        );
    }
}
