package com.it.academy.library.model.mapper.filter.book;

import com.it.academy.library.dto.filter.book.BookSeriesFilter;
import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookSeriesFilterMapper implements Mapper<BookSeries, BookSeriesFilter> {
    @Override
    public BookSeriesFilter map(@NotNull BookSeries object) {
        return new BookSeriesFilter(object.getName());
    }
}
