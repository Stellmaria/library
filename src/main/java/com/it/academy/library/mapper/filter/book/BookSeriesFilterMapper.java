package com.it.academy.library.mapper.filter.book;

import com.it.academy.library.dto.filter.book.BookSeriesFilter;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookSeries;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookSeriesFilterMapper implements Mapper<BookSeries, BookSeriesFilter> {
    @Override
    public BookSeriesFilter map(@NotNull BookSeries object) {
        return new BookSeriesFilter(object.getName());
    }
}
