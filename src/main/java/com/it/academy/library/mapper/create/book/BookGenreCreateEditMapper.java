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
        return create(fromObject);
    }

    @Override
    public BookGenre map(@NotNull BookGenreCreateEditDto fromObject) {
        return create(fromObject);
    }

    private BookGenre create(@NotNull BookGenreCreateEditDto fromObject) {
        return BookGenre.builder()
                .name(fromObject.getName())
                .description(fromObject.getDescription())
                .build();
    }
}
