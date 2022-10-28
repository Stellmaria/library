package com.it.academy.library.model.mapper.read.book;

import com.it.academy.library.dto.read.book.BookFormatReadDto;
import com.it.academy.library.dto.read.book.BookLanguageReadDto;
import com.it.academy.library.dto.read.book.BookPublishingHouseReadDto;
import com.it.academy.library.dto.read.book.BookReadDto;
import com.it.academy.library.dto.read.book.BookSeriesReadDto;
import com.it.academy.library.dto.read.book.BookStatusReadDto;
import com.it.academy.library.dto.read.order.OrderReadDto;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.mapper.Mapper;
import com.it.academy.library.model.mapper.read.order.OrderReadMapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookReadMapper implements Mapper<Book, BookReadDto> {
    private final BookStatusReadMapper bookStatusReadMapper;
    private final BookLanguageReadMapper bookLanguageReadMapper;
    private final BookFormatReadMapper bookFormatReadMapper;
    private final BookPublishingHouseReadMapper bookPublishingHouseReadMapper;
    private final BookSeriesReadMapper bookSeriesReadMapper;
    private final OrderReadMapper orderReadMapper;

    @Override
    public BookReadDto map(@NotNull Book object) {
        return new BookReadDto(
                object.getId(),
                object.getTitle(),
                object.getSubtitle(),
                object.getYear(),
                object.getPage(),
                object.getIsbn10(),
                object.getIsbn13(),
                object.getImagePath(),
                getBookStatus(object),
                getBookLanguage(object),
                getBookFormat(object),
                getBookPublishingHouse(object),
                getBookSeries(object),
                getOrder(object)
        );
    }

    private OrderReadDto getOrder(@NotNull Book object) {
        return Optional.ofNullable(object.getOrder())
                .map(orderReadMapper::map)
                .orElse(null);
    }

    private BookSeriesReadDto getBookSeries(@NotNull Book object) {
        return Optional.ofNullable(object.getBookSeries())
                .map(bookSeriesReadMapper::map)
                .orElse(null);
    }

    private BookPublishingHouseReadDto getBookPublishingHouse(@NotNull Book object) {
        return Optional.ofNullable(object.getBookPublishingHouse())
                .map(bookPublishingHouseReadMapper::map)
                .orElse(null);
    }

    private BookFormatReadDto getBookFormat(@NotNull Book object) {
        return Optional.ofNullable(object.getBookFormat())
                .map(bookFormatReadMapper::map)
                .orElse(null);
    }

    private BookLanguageReadDto getBookLanguage(@NotNull Book object) {
        return Optional.ofNullable(object.getBookLanguage())
                .map(bookLanguageReadMapper::map)
                .orElse(null);
    }

    private BookStatusReadDto getBookStatus(@NotNull Book object) {
        return Optional.ofNullable(object.getBookStatus())
                .map(bookStatusReadMapper::map)
                .orElse(null);
    }
}
