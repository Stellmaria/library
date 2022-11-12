package com.it.academy.library.mapper.filter.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.BookAdditional;
import com.it.academy.library.service.dto.filter.book.BookAdditionalFilter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookAdditionalFilterMapper implements Mapper<BookAdditional, BookAdditionalFilter> {
    private final BookFilterMapper bookFilterMapper;

    @Override
    public BookAdditionalFilter map(@NotNull BookAdditional object) {
        var book = Optional.ofNullable(object.getBook())
                .map(bookFilterMapper::map)
                .orElse(null);

        return new BookAdditionalFilter(
                object.getId(),
                book,
                object.getVolume(),
                object.getSerialNo(),
                object.getPrice(),
                object.getLink()
        );
    }
}
