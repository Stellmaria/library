package com.it.academy.library.mapper.create.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.entity.book.BookAdditional;
import com.it.academy.library.model.repository.entity.book.BookRepository;
import com.it.academy.library.service.dto.create.book.BookAdditionalCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookAdditionalCreateEditMapper implements Mapper<BookAdditionalCreateEditDto, BookAdditional> {
    public final BookRepository bookRepository;

    @Override
    public BookAdditional map(@NotNull BookAdditionalCreateEditDto fromObject, @NotNull BookAdditional toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    @Override
    public BookAdditional map(@NotNull BookAdditionalCreateEditDto object) {
        var bookAdditional = new BookAdditional();

        copy(object, bookAdditional);

        return bookAdditional;
    }

    private void copy(@NotNull BookAdditionalCreateEditDto object, @NotNull BookAdditional bookAdditional) {
        bookAdditional.setBook(getBook(object.getBookId()));
        bookAdditional.setVolume(object.getVolume());
        bookAdditional.setSerialNo(object.getSerialNo());
        bookAdditional.setPrice(object.getPrice());
        bookAdditional.setLink(object.getLink());
    }

    private Book getBook(Long id) {
        return Optional.ofNullable(id)
                .flatMap(bookRepository::findById)
                .orElse(null);
    }
}
