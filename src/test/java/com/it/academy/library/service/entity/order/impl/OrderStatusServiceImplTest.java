package com.it.academy.library.service.entity.order.impl;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.model.repository.entity.order.OrderStatusRepository;
import com.it.academy.library.service.entity.order.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@DisplayName("Order status service test.")
class OrderStatusServiceImplTest extends IntegrationTestBase {
    private final OrderStatusRepository orderStatusRepository;

    private final OrderStatusService orderStatusService;

    @Test
    @DisplayName("Find all order status.")
    void findAll() {
        var expected = orderStatusRepository.count();

        var actual = orderStatusService.findAll();

        assertThat(actual).hasSize(Math.toIntExact(expected));
    }
}
