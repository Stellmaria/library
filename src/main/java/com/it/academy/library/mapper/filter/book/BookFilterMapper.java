package com.it.academy.library.mapper.filter.book;

import com.it.academy.library.dto.filter.book.BookFilter;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookFilterMapper implements Mapper<Book, BookFilter> {
    @Override
    public BookFilter map(@NotNull Book object) {
        return new BookFilter(
                object.getId(),
                object.getTitle(),
                object.getSubtitle(),
                object.getYear(),
                object.getPage(),
                object.getIsbn10(),
                object.getIsbn13(),
                object.getBookStatus() != null ? object.getBookStatus().getId() : null,
                object.getBookLanguage() != null ? object.getBookLanguage().getId() : null,
                object.getBookFormat() != null ? object.getBookFormat().getId() : null,
                object.getBookPublishingHouse() != null ? object.getBookPublishingHouse().getId() : null,
                object.getBookSeries() != null ? object.getBookSeries().getId() : null,
                object.getOrder() != null ? object.getOrder().getId() : null
        );
    }
}
