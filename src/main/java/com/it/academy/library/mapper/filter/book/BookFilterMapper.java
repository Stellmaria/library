package com.it.academy.library.mapper.filter.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.mapper.filter.order.OrderFilterMapper;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import com.it.academy.library.service.dto.filter.book.BookFormatFilter;
import com.it.academy.library.service.dto.filter.book.BookLanguageFilter;
import com.it.academy.library.service.dto.filter.book.BookPublishingHouseFilter;
import com.it.academy.library.service.dto.filter.book.BookSeriesFilter;
import com.it.academy.library.service.dto.filter.book.BookStatusFilter;
import com.it.academy.library.service.dto.filter.order.OrderFilter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookFilterMapper implements Mapper<Book, BookFilter> {
    private final BookStatusFilterMapper bookStatusFilterMapper;
    private final BookLanguageFilterMapper bookLanguageFilterMapper;
    private final BookFormatFilterMapper bookFormatFilterMapper;
    private final BookPublishingHouseFilterMapper bookPublishingHouseFilterMapper;
    private final BookSeriesFilterMapper bookSeriesFilterMapper;
    private final OrderFilterMapper orderFilterMapper;

    @Override
    public BookFilter map(@NotNull Book object) {
        return new BookFilter(
                object.getId(),
                object.getTitle(),
                object.getSubtitle(),
                object.getYear(),
                object.getPages(),
                object.getIsbn10(),
                object.getIsbn13(),
                getBookStatus(object),
                getBookLanguage(object),
                getBookFormat(object),
                getBookPublishingHouse(object),
                getBookSeries(object),
                getOrder(object)
        );
    }

    @Nullable
    private OrderFilter getOrder(@NotNull Book object) {
        return Optional.ofNullable(object.getOrder())
                .map(orderFilterMapper::map)
                .orElse(null);
    }

    @Nullable
    private BookSeriesFilter getBookSeries(@NotNull Book object) {
        return Optional.ofNullable(object.getBookSeries())
                .map(bookSeriesFilterMapper::map)
                .orElse(null);
    }

    @Nullable
    private BookPublishingHouseFilter getBookPublishingHouse(@NotNull Book object) {
        return Optional.ofNullable(object.getBookPublishingHouse())
                .map(bookPublishingHouseFilterMapper::map)
                .orElse(null);
    }

    @Nullable
    private BookFormatFilter getBookFormat(@NotNull Book object) {
        return Optional.ofNullable(object.getBookFormat())
                .map(bookFormatFilterMapper::map)
                .orElse(null);
    }

    @Nullable
    private BookLanguageFilter getBookLanguage(@NotNull Book object) {
        return Optional.ofNullable(object.getBookLanguage())
                .map(bookLanguageFilterMapper::map)
                .orElse(null);
    }

    @Nullable
    private BookStatusFilter getBookStatus(@NotNull Book object) {
        return Optional.ofNullable(object.getBookStatus())
                .map(bookStatusFilterMapper::map)
                .orElse(null);
    }
}
