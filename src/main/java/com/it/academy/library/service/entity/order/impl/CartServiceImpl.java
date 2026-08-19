package com.it.academy.library.service.entity.order.impl;

import com.it.academy.library.exception.NotEnoughBooksInStockException;
import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.convert.book.BookMapper;
import com.it.academy.library.mapper.convert.user.UserMapper;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.model.repository.entity.book.BookRepository;
import com.it.academy.library.model.repository.entity.order.OrderRepository;
import com.it.academy.library.service.dto.read.book.BookReadDto;
import com.it.academy.library.service.dto.read.user.UserReadDto;
import com.it.academy.library.service.entity.order.CartService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
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
public class CartServiceImpl implements CartService {
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    private final BookMapper bookMapper;
    private final UserMapper userMapper;

    private final ApplicationEventPublisher eventPublisher;

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

    @Transactional(rollbackFor = Exception.class)
    public void checkout(UserReadDto user) throws NotEnoughBooksInStockException {
        if (books.isEmpty()) {
            throw new IllegalStateException("Cannot checkout an empty cart");
        }

        var inventory = new HashMap<Book, Long>();

        for (Map.Entry<Book, Long> entry : books.entrySet()) {
            var requestedQuantity = entry.getValue();
            var bookId = entry.getKey().getId();
            var book = bookRepository.findByIdForUpdate(bookId)
                    .orElseThrow(() -> new IllegalStateException("Book not found: " + bookId));

            eventPublisher.publishEvent(new EntityEvent(book, AccessType.READ));

            if (requestedQuantity <= 0 || book.getQuantity() < requestedQuantity) {
                throw new NotEnoughBooksInStockException(book);
            }

            inventory.put(book, requestedQuantity);
        }

        var order = createNewOrder(user);

        inventory.forEach((book, requestedQuantity) -> {
            book.setQuantity(book.getQuantity() - requestedQuantity);
            book.setOrder(order);
        });

        bookRepository.saveAllAndFlush(inventory.keySet());
        eventPublisher.publishEvent(new EntityEvent(inventory.keySet(), AccessType.UPDATE));

        books.clear();
    }

    private Order createNewOrder(UserReadDto user) {
        var order = orderRepository.saveAndFlush(createOrder(user));
        eventPublisher.publishEvent(new EntityEvent(order, AccessType.CREATE));
        return order;
    }

    private @NotNull Order createOrder(UserReadDto user) {
        var now = LocalDateTime.now();
        var date = LocalDateTime.of(
                now.getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), now.getMinute()
        );

        var orderStatus = new OrderStatus();
        orderStatus.setId(1);

        var order = new Order();

        order.setUser(userMapper.map(user));
        order.setOrderStatus(orderStatus);
        order.setOrderDate(date);
        order.setReturnDate(date.plusMonths(1));
        order.setCreatedAt(date.toInstant(ZoneOffset.UTC));
        order.setCreatedBy(user.getUsername());

        return order;
    }
}
