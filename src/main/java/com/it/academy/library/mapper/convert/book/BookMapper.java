package com.it.academy.library.mapper.convert.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.mapper.convert.author.AuthorMapper;
import com.it.academy.library.mapper.convert.order.OrderMapper;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.service.dto.read.book.BookReadDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMapper implements Mapper<BookReadDto, Book> {
    private final BookStatusMapper bookStatusMapper;
    private final BookLanguageMapper bookLanguageMapper;
    private final BookFormatMapper bookFormatMapper;
    private final BookPublishingHouseMapper bookPublishingHouseMapper;
    private final BookSeriesMapper bookSeriesMapper;
    private final OrderMapper orderMapper;
    private final BookGenreMapper bookGenreMapper;
    private final AuthorMapper authorMapper;

    @Override
    public Book map(@NotNull BookReadDto object) {
        return new Book(
                object.getId(),
                object.getTitle(),
                object.getSubtitle(),
                object.getYear(),
                object.getQuantity(),
                object.getPages(),
                object.getIsbn10(),
                object.getIsbn13(),
                object.getImage(),
                Optional.ofNullable(object.getBookStatus())
                        .map(bookStatusMapper::map)
                        .orElse(null),
                Optional.ofNullable(object.getBookLanguage())
                        .map(bookLanguageMapper::map)
                        .orElse(null),
                Optional.ofNullable(object.getBookFormat())
                        .map(bookFormatMapper::map)
                        .orElse(null),
                Optional.ofNullable(object.getBookPublishingHouse())
                        .map(bookPublishingHouseMapper::map)
                        .orElse(null),
                Optional.ofNullable(object.getBookSeries())
                        .map(bookSeriesMapper::map)
                        .orElse(null),
                Optional.ofNullable(object.getOrder())
                        .map(orderMapper::map)
                        .orElse(null),
                Optional.of(object.getAuthors().stream()
                                .map(authorMapper::map)
                                .collect(Collectors.toList()))
                        .orElse(null),
                Optional.of(object.getGenres().stream()
                                .map(bookGenreMapper::map)
                                .collect(Collectors.toList()))
                        .orElse(null)
        );
    }
}
