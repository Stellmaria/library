package com.it.academy.library.mapper.create.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.service.dto.create.book.BookSeriesCreateEditDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookSeriesCreateEditMapper implements Mapper<BookSeriesCreateEditDto, BookSeries> {
    @Override
    public BookSeries map(@NotNull BookSeriesCreateEditDto object) {
        var bookSeries = new BookSeries();

        bookSeries.setName(object.getName());

        return bookSeries;
    }
}
