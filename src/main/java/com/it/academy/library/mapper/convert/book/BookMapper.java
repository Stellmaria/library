package com.it.academy.library.mapper.convert.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.mapper.convert.AuthorMapper;
import com.it.academy.library.mapper.convert.order.OrderMapper;
import com.it.academy.library.model.entity.Author;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.model.entity.book.BookLanguage;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.service.dto.read.book.BookReadDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
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
        var book = new Book();

        book.setCreatedAt(object.getCreateAt().toInstant(ZoneOffset.UTC));
        book.setCreatedBy(object.getCreateBy());
        book.setId(object.getId());
        book.setTitle(object.getTitle());
        book.setSubtitle(object.getSubtitle());
        book.setYear(object.getYear());
        book.setQuantity(object.getQuantity());
        book.setIsbn10(object.getIsbn10());
        book.setIsbn13(object.getIsbn13());
        book.setImage(object.getImage());
        book.setBookStatus(getBookStatus(object));
        book.setBookLanguage(getBookLanguage(object));
        book.setBookFormat(getBookFormat(object));
        book.setBookPublishingHouse(getBookPublishingHouse(object));
        book.setBookSeries(getBookSeries(object));
        book.setOrder(getOrder(object));
        book.setAuthors(getAuthors(object));
        book.setGenres(getGenres(object));

        return book;
    }

    private List<BookGenre> getGenres(@NotNull BookReadDto object) {
        return Optional.of(object.getGenres().stream()
                        .map(bookGenreMapper::map)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    private List<Author> getAuthors(@NotNull BookReadDto object) {
        return Optional.of(object.getAuthors().stream()
                        .map(authorMapper::map)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    private Order getOrder(@NotNull BookReadDto object) {
        return Optional.ofNullable(object.getOrder())
                .map(orderMapper::map)
                .orElse(null);
    }

    private BookSeries getBookSeries(@NotNull BookReadDto object) {
        return Optional.ofNullable(object.getBookSeries())
                .map(bookSeriesMapper::map)
                .orElse(null);
    }

    private BookPublishingHouse getBookPublishingHouse(@NotNull BookReadDto object) {
        return Optional.ofNullable(object.getBookPublishingHouse())
                .map(bookPublishingHouseMapper::map)
                .orElse(null);
    }

    private BookFormat getBookFormat(@NotNull BookReadDto object) {
        return Optional.ofNullable(object.getBookFormat())
                .map(bookFormatMapper::map)
                .orElse(null);
    }

    private BookLanguage getBookLanguage(@NotNull BookReadDto object) {
        return Optional.ofNullable(object.getBookLanguage())
                .map(bookLanguageMapper::map)
                .orElse(null);
    }

    private BookStatus getBookStatus(@NotNull BookReadDto object) {
        return Optional.ofNullable(object.getBookStatus())
                .map(bookStatusMapper::map)
                .orElse(null);
    }
}
