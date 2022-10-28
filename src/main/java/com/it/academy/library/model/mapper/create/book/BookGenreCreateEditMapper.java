package com.it.academy.library.model.mapper.create.book;

import com.it.academy.library.dto.create.book.BookGenreCreateEditDto;
import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookGenreCreateEditMapper implements Mapper<BookGenreCreateEditDto, BookGenre> {
    @Override
    public BookGenre map(@NotNull BookGenreCreateEditDto object) {
        var bookGenre = new BookGenre();
        bookGenre.setName(object.getName());
        return bookGenre;
    }
}
