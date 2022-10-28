package com.it.academy.library.model.mapper.create.book;

import com.it.academy.library.dto.create.book.BookSeriesCreateEditDto;
import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.model.mapper.Mapper;
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
