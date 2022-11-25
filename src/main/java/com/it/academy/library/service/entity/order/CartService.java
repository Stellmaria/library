package com.it.academy.library.service.entity.order;

import com.it.academy.library.exception.NotBookException;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.service.dto.read.book.BookReadDto;
import com.it.academy.library.service.dto.read.user.UserReadDto;

import java.util.Map;

public interface CartService {
    /**
     * Adding a book to the cart.
     *
     * @param dto to add to cart.
     * @return map where book is the key and long is the number of books.
     */
    Map<Book, Long> addBook(BookReadDto dto);

    /**
     * Removing a book from the basket.
     *
     * @param dto for removing.
     * @return map where book is the key and long is the number of books.
     */
    Map<Book, Long> removeBook(BookReadDto dto);

    /**
     * Validation of the order with the number of books from the database.
     *
     * @param user for check.
     * @throws NotBookException if the quantity does not match,
     *                          it gives an error with a description of the problem.
     */
    void checkout(UserReadDto user) throws NotBookException;

    /**
     * Getter.
     *
     * @return map where book is the key and long is the number of books.
     */
    Map<Book, Long> getBooks();
}
