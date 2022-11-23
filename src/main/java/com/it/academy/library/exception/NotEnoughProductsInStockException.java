package com.it.academy.library.exception;

import com.it.academy.library.model.entity.book.Book;

public class NotEnoughProductsInStockException extends Exception {
    public NotEnoughProductsInStockException(Book book) {
        super(String.format("Not enough %s products in stock. Only %d left", book.getTitle(), book.getQuantity()));
    }
}
