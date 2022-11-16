package com.it.academy.library.mapper.read.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.mapper.filter.book.BookSeriesFilterMapper;
import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.model.repository.entity.book.BookRepository;
import com.it.academy.library.service.dto.read.book.BookSeriesReadDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookSeriesReadMapper implements Mapper<BookSeries, BookSeriesReadDto> {
    private final BookRepository bookRepository;
    private final BookSeriesFilterMapper bookSeriesFilterMapper;

    @Override
    public BookSeriesReadDto map(@NotNull BookSeries object) {
        return new BookSeriesReadDto(
                object.getId(),
                object.getName(),
                Optional.of(bookRepository.findAllByBookSeriesFilter(bookSeriesFilterMapper.map(object)))
                        .orElse(null)
        );
    }
}
