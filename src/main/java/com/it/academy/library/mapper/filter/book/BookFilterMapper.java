package com.it.academy.library.mapper.filter.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.mapper.filter.order.OrderFilterMapper;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
        var bookStatus = Optional.ofNullable(object.getBookStatus())
                .map(bookStatusFilterMapper::map)
                .orElse(null);
        var bookLanguage = Optional.ofNullable(object.getBookLanguage())
                .map(bookLanguageFilterMapper::map)
                .orElse(null);
        var bookFormat = Optional.ofNullable(object.getBookFormat())
                .map(bookFormatFilterMapper::map)
                .orElse(null);
        var bookPublishingHouse = Optional.ofNullable(object.getBookPublishingHouse())
                .map(bookPublishingHouseFilterMapper::map)
                .orElse(null);
        var bookSeries = Optional.ofNullable(object.getBookSeries())
                .map(bookSeriesFilterMapper::map)
                .orElse(null);
        var order = Optional.ofNullable(object.getOrder())
                .map(orderFilterMapper::map)
                .orElse(null);

        return new BookFilter(
                object.getId(),
                object.getTitle(),
                object.getSubtitle(),
                object.getYear(),
                object.getPages(),
                object.getIsbn10(),
                object.getIsbn13(),
                bookStatus,
                bookLanguage,
                bookFormat,
                bookPublishingHouse,
                bookSeries,
                order
        );
    }
}
