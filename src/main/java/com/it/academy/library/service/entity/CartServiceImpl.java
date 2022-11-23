package com.it.academy.library.service.entity;

import com.it.academy.library.exception.NotEnoughProductsInStockException;
import com.it.academy.library.mapper.convert.book.BookMapper;
import com.it.academy.library.mapper.convert.user.UserMapper;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.model.repository.entity.book.BookRepository;
import com.it.academy.library.model.repository.entity.order.OrderRepository;
import com.it.academy.library.service.dto.read.book.BookReadDto;
import com.it.academy.library.service.dto.read.user.UserReadDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl {
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    private final BookMapper bookMapper;
    private final UserMapper userMapper;
    @Getter
    private final Map<Book, Long> books = new HashMap<>();

    public Map<Book, Long> addBook(BookReadDto dto) {
        var book = bookMapper.map(dto);

        if (books.containsKey(book)) {
            books.replace(book, books.get(book) + 1);
        } else {
            books.put(book, 1L);
        }
        return books;
    }

    public Map<Book, Long> removeBook(BookReadDto dto) {
        var book = bookMapper.map(dto);

        if (books.containsKey(book)) {
            if (books.get(book) > 1) {
                books.replace(book, books.get(book) - 1);
            } else if (books.get(book) == 1) {
                books.remove(book);
            }
        }
        return books;
    }

    public void checkout(UserReadDto user) throws NotEnoughProductsInStockException {
        for (Map.Entry<Book, Long> entry : books.entrySet()) {
            var book = bookRepository.findById(entry.getKey().getId());
            assert book.isPresent();

            if (book.get().getQuantity() < entry.getValue()) {
                throw new NotEnoughProductsInStockException(book.get());
            }

            entry.getKey().setQuantity(book.get().getQuantity() - entry.getValue());
        }
        bookRepository.saveAllAndFlush(books.keySet());
        orderRepository.saveAndFlush(getOrder(user));

        books.clear();
    }

    private @NotNull Order getOrder(UserReadDto user) {
        var order = new Order();

        order.setUser(userMapper.map(user));
        order.setOrderStatus(OrderStatus.builder().id(1).build());
        var orderDate = LocalDateTime.now();
        order.setOrderDate(orderDate);
        order.setReturnDate(orderDate.plusMonths(1));
        order.setCreatedAt(orderDate.toInstant(ZoneOffset.UTC));
        order.setCreatedBy(user.getUsername());

        return order;
    }
}
