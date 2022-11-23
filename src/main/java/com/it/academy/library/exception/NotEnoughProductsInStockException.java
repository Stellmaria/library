package com.it.academy.library.exception;

import com.it.academy.library.model.entity.book.Book;
import org.jetbrains.annotations.NotNull;

public class NotEnoughProductsInStockException extends Exception {
    public NotEnoughProductsInStockException(@NotNull Book product) {
        super(String.format("Not enough %s products in stock. Only %d left", product.getTitle(), product.getQuantity()));
    }
}
