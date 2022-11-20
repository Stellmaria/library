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
        copy(fromObject, toObject);

        return toObject;
    }

    @Override
    public BookSeries map(@NotNull BookSeriesCreateEditDto object) {
        var bookSeries = new BookSeries();

        copy(object, bookSeries);

        return bookSeries;
    }

    private void copy(@NotNull BookSeriesCreateEditDto object, @NotNull BookSeries bookSeries) {
        bookSeries.setName(object.getName());
    }
}
