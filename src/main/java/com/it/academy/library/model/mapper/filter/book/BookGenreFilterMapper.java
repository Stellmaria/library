package com.it.academy.library.model.mapper.filter.book;

import com.it.academy.library.dto.filter.book.BookGenreFilter;
import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookGenreFilterMapper implements Mapper<BookGenre, BookGenreFilter> {
    @Override
    public BookGenreFilter map(@NotNull BookGenre object) {
        return new BookGenreFilter(
                object.getName(),
                object.getDescription()
        );
    }
}
