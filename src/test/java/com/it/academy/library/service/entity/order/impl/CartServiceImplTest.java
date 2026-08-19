package com.it.academy.library.service.entity.order.impl;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.exception.NotEnoughBooksInStockException;
import com.it.academy.library.mapper.convert.book.BookMapper;
import com.it.academy.library.mapper.read.book.BookSeriesReadMapper;
import com.it.academy.library.mapper.read.book.BookStatusReadMapper;
import com.it.academy.library.model.repository.entity.book.BookRepository;
import com.it.academy.library.model.repository.entity.order.OrderRepository;
import com.it.academy.library.service.entity.order.CartService;
import com.it.academy.library.service.entity.user.UserService;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
@DisplayName("Cart service test.")
class CartServiceImplTest extends IntegrationTestBase {
    private final CartService cartService;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;

    private final BookMapper bookMapper;
    private final BookStatusReadMapper bookStatusReadMapper;
    private final BookSeriesReadMapper bookSeriesReadMapper;

    @BeforeEach
    void clearCart() {
        cartService.getBooks().clear();
    }

    @Test
    @DisplayName("Add book to cart.")
    void addBook() {
        var expectedSize = 1L;
        var expected = ConstantUtil.getDarkTownBook(bookStatusReadMapper, bookSeriesReadMapper);

        var actual = cartService.addBook(expected);

        assertAll(
                () -> assertEquals(expectedSize, actual.size()),
                () -> assertFalse(actual.isEmpty()),
                () -> assertTrue(actual.containsKey(bookMapper.map(expected))),
                () -> assertThat(actual).containsEntry(bookMapper.map(expected), expectedSize)
        );
    }

    @Test
    @DisplayName("Remove book from cart.")
    void removeBook() {
        var expectedSize = 0L;
        var expected = ConstantUtil.getDarkTownBook(bookStatusReadMapper, bookSeriesReadMapper);
        cartService.addBook(expected);

        var actual = cartService.removeBook(expected);

        assertAll(
                () -> assertEquals(expectedSize, actual.size()),
                () -> assertTrue(actual.isEmpty()),
                () -> assertFalse(actual.containsKey(bookMapper.map(expected))),
                () -> assertThat(actual).doesNotContainEntry(bookMapper.map(expected), 0L)
        );
    }

    @Test
    @DisplayName("Checkout does not create an order when requested stock is unavailable.")
    void checkoutDoesNotCreateOrderWhenStockIsInsufficient() {
        var bookDto = ConstantUtil.getDarkTownBook(bookStatusReadMapper, bookSeriesReadMapper);
        var user = userService.findByUsername(ConstantUtil.USER_USERNAME_READER).orElseThrow();
        var ordersBefore = orderRepository.count();
        var quantityBefore = bookRepository.findById(ConstantUtil.BOOK_ID_22).orElseThrow().getQuantity();

        for (int i = 0; i < quantityBefore + 1; i++) {
            cartService.addBook(bookDto);
        }

        assertThrows(NotEnoughBooksInStockException.class, () -> cartService.checkout(user));

        assertAll(
                () -> assertEquals(ordersBefore, orderRepository.count()),
                () -> assertEquals(
                        quantityBefore,
                        bookRepository.findById(ConstantUtil.BOOK_ID_22).orElseThrow().getQuantity()
                ),
                () -> assertFalse(cartService.getBooks().isEmpty())
        );
    }
}
