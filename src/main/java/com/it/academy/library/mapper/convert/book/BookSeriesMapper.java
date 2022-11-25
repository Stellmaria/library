package com.it.academy.library.mapper.convert.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.service.dto.read.book.BookSeriesReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookSeriesMapper implements Mapper<BookSeriesReadDto, BookSeries> {
    @Override
    public BookSeries map(@NotNull BookSeriesReadDto object) {
        return BookSeries.builder()
                .id(object.getId())
                .name(object.getName())
                .build();
    }
}
