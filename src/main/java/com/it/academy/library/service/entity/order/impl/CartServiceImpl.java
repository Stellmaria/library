package com.it.academy.library.service.entity.order.impl;

import com.it.academy.library.exception.NotEnoughBooksInStockException;
import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.convert.book.BookMapper;
import com.it.academy.library.mapper.convert.user.UserMapper;
import com.it.academy.library.mapper.filter.order.OrderFilterMapper;
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
import java.util.Objects;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    private final BookMapper bookMapper;
    private final UserMapper userMapper;
    private final OrderFilterMapper orderFilterMapper;

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

    public void checkout(UserReadDto user) throws NotEnoughBooksInStockException {
        var order = new Order();
        order.setId(createNewOrder(user).getId());

        for (Map.Entry<Book, Long> entry : books.entrySet()) {
            var book = bookRepository.findById(entry.getKey().getId()).orElse(null);
            eventPublisher.publishEvent(new EntityEvent(book, AccessType.READ));

            if (Objects.requireNonNull(book).getQuantity() < entry.getValue()) {
                throw new NotEnoughBooksInStockException(book);
            }

            entry.getKey().setQuantity(book.getQuantity() - entry.getValue());
            entry.getKey().setOrder(order);
        }
        bookRepository.saveAllAndFlush(books.keySet());
        eventPublisher.publishEvent(new EntityEvent(books.keySet(), AccessType.UPDATE));

        books.clear();
    }

    private Order createNewOrder(UserReadDto user) {
        var order = orderRepository.saveAndFlush(createOrder(user));
        eventPublisher.publishEvent(new EntityEvent(order, AccessType.CREATE));

        var orderFilter = orderFilterMapper.map(order);

        return Objects.requireNonNull(orderRepository.findAllByOrderFilter(orderFilter).stream()
                .findFirst()
                .orElse(null)
        );
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
