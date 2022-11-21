package com.it.academy.library.mapper.create.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.service.dto.create.book.BookGenreCreateEditDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookGenreCreateEditMapper implements Mapper<BookGenreCreateEditDto, BookGenre> {
    @Override
    public BookGenre map(@NotNull BookGenreCreateEditDto fromObject, @NotNull BookGenre toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    @Override
    public BookGenre map(@NotNull BookGenreCreateEditDto object) {
        var bookGenre = new BookGenre();

        copy(object, bookGenre);

        return bookGenre;
    }

    private void copy(@NotNull BookGenreCreateEditDto object, @NotNull BookGenre bookGenre) {
        bookGenre.setName(object.getName());
        bookGenre.setDescription(object.getDescription());
    }
}
