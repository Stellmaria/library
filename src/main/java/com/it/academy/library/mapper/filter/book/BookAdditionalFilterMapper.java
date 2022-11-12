package com.it.academy.library.mapper.filter.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookAdditional;
import com.it.academy.library.service.dto.filter.book.BookAdditionalFilter;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookAdditionalFilterMapper implements Mapper<BookAdditional, BookAdditionalFilter> {
    private final BookFilterMapper bookFilterMapper;

    @Override
    public BookAdditionalFilter map(@NotNull BookAdditional object) {
        return new BookAdditionalFilter(
                object.getId(),
                getBook(object),
                object.getVolume(),
                object.getSerialNo(),
                object.getPrice(),
                object.getLink()
        );
    }

    @Nullable
    private BookFilter getBook(@NotNull BookAdditional object) {
        return Optional.ofNullable(object.getBook())
                .map(bookFilterMapper::map)
                .orElse(null);
    }
}
