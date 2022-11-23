package com.it.academy.library.mapper.convert.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.service.dto.read.book.BookGenreReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookGenreMapper implements Mapper<BookGenreReadDto, BookGenre> {
    @Override
    public BookGenre map(@NotNull BookGenreReadDto object) {
        var bookGenre = new BookGenre();

        bookGenre.setId(object.getId());
        bookGenre.setName(object.getName());
        bookGenre.setDescription(object.getDescription());

        return bookGenre;
    }
}
