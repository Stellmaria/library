package com.it.academy.library.exception;

import com.it.academy.library.model.entity.book.Book;
import org.jetbrains.annotations.NotNull;

public class NotBookException extends Exception {
    public NotBookException(@NotNull Book book) {
        super(String.format("Not enough %s books in stock. Only %d left", book.getTitle(), book.getQuantity()));
    }
}
