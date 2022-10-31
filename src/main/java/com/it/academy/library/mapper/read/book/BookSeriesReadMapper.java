package com.it.academy.library.mapper.read.book;

import com.it.academy.library.dto.read.book.BookSeriesReadDto;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookSeries;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookSeriesReadMapper implements Mapper<BookSeries, BookSeriesReadDto> {
    @Override
    public BookSeriesReadDto map(@NotNull BookSeries object) {
        return new BookSeriesReadDto(
                object.getId(),
                object.getName()
        );
    }
}
