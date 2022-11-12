package com.it.academy.library.mapper.filter.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.service.dto.filter.book.BookGenreFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookGenreFilterMapper implements Mapper<BookGenre, BookGenreFilter> {
    @Override
    public BookGenreFilter map(@NotNull BookGenre object) {
        return new BookGenreFilter(
                object.getId(),
                object.getName(),
                object.getDescription()
        );
    }
}
