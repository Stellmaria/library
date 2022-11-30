package com.it.academy.library.service.entity.order.impl;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.model.repository.entity.order.OrderRepository;
import com.it.academy.library.service.dto.create.OrderCreateEditDto;
import com.it.academy.library.service.dto.filter.order.OrderFilter;
import com.it.academy.library.service.dto.read.order.OrderReadDto;
import com.it.academy.library.service.dto.read.order.OrderStatusReadDto;
import com.it.academy.library.service.dto.read.user.UserReadDto;
import com.it.academy.library.service.entity.order.OrderService;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
@DisplayName("Order service test.")
class OrderServiceImplTest extends IntegrationTestBase {
    private final OrderRepository orderRepository;

    private final OrderService orderService;

    @Test
    @DisplayName("Find order by id.")
    void findById() {
        var expected = new OrderReadDto(
                1L,
                UserReadDto.builder().id(3L).build(),
                OrderStatusReadDto.builder().id(3).build(),
                LocalDateTime.of(2022, 10, 23, 10, 0, 0),
                LocalDateTime.of(2022, 10, 17, 10, 0, 0)
        );

        var actual = orderService.findById(expected.getId());

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(expected.getId(), entity.getId()),
                        () -> assertEquals(expected.getUser().getId(), entity.getUser().getId()),
                        () -> assertEquals(expected.getOrderStatus().getId(), entity.getOrderStatus().getId()),
                        () -> assertEquals(expected.getOrderDate(), entity.getOrderDate()),
                        () -> assertEquals(expected.getReturnDate(), entity.getReturnDate())
                )
        );
    }

    @Test
    @DisplayName("Find all order.")
    void findAll() {
        var expected = orderRepository.count();

        var actual = orderService.findAll();

        assertThat(actual).hasSize(Math.toIntExact(expected));
    }

    @Test
    @DisplayName("Find all order with filter.")
    void findAllWithFilter() {
        var expected = 1;
        var filter = new OrderFilter();
        filter.setOrderDate(ConstantUtil.ORDER_DATE_2022_10_25_T_18_00);

        var actual = orderService.findAll(filter, Pageable.ofSize(ConstantUtil.PAGE_SIZE));

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all order by user id.")
    void findByUserId() {
        var expected = 2;

        var actual = orderService.findByUserId(3L);

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Update order.")
    void update() {
        var order = new OrderCreateEditDto(
                4L,
                1,
                LocalDateTime.of(2022, 11, 30, 18, 0),
                LocalDateTime.of(2022, 12, 30, 18, 0)
        );

        var actual = orderService.update(1L, order);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(order.getOrderStatusId(), entity.getOrderStatus().getId()),
                        () -> assertEquals(order.getOrderDate(), entity.getOrderDate()),
                        () -> assertEquals(order.getReturnDate(), entity.getReturnDate()),
                        () -> assertEquals(order.getUserId(), entity.getUser().getId())
                )
        );
    }

    @Test
    @DisplayName("Delete order.")
    void delete() {
        assertAll(
                () -> assertTrue(orderService.delete(ConstantUtil.ORDER_ID_2)),
                () -> assertFalse(orderService.delete(99L))
        );
    }
}