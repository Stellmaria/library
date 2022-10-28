package com.it.academy.library.model.mapper.read.book;

import com.it.academy.library.dto.read.book.BookAdditionalReadDto;
import com.it.academy.library.dto.read.book.BookReadDto;
import com.it.academy.library.model.entity.book.BookAdditional;
import com.it.academy.library.model.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookAdditionalReadMapper implements Mapper<BookAdditional, BookAdditionalReadDto> {
    private final BookReadMapper bookReadMapper;

    @Override
    public BookAdditionalReadDto map(@NotNull BookAdditional object) {
        return new BookAdditionalReadDto(
                object.getId(),
                getBook(object),
                object.getVolume(),
                object.getSerialNo(),
                object.getPrice(),
                object.getLink()
        );
    }

    private BookReadDto getBook(@NotNull BookAdditional object) {
        return Optional.ofNullable(object.getBook())
                .map(bookReadMapper::map)
                .orElse(null);
    }
}
