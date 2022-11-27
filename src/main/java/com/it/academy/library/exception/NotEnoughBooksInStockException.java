package com.it.academy.library.exception;

import com.it.academy.library.model.entity.book.Book;
import org.jetbrains.annotations.NotNull;

public class NotEnoughBooksInStockException extends Exception {
    public NotEnoughBooksInStockException(@NotNull Book product) {
        super(String.format("Not enough %s books in stock. Only %d left", product.getTitle(), product.getQuantity()));
    }
}
