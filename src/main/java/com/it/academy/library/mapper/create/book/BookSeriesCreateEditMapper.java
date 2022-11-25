package com.it.academy.library.mapper.create.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.service.dto.create.book.BookSeriesCreateEditDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookSeriesCreateEditMapper implements Mapper<BookSeriesCreateEditDto, BookSeries> {
    @Override
    public BookSeries map(@NotNull BookSeriesCreateEditDto fromObject, @NotNull BookSeries toObject) {
        return create(fromObject);
    }

    @Override
    public BookSeries map(@NotNull BookSeriesCreateEditDto object) {
        return create(object);
    }

    private BookSeries create(@NotNull BookSeriesCreateEditDto object) {
        return BookSeries.builder()
                .name(object.getName())
                .build();
    }
}
