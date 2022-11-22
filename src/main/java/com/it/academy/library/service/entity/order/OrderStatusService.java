package com.it.academy.library.service.entity.order;

import com.it.academy.library.service.dto.read.order.OrderStatusReadDto;

import java.util.Collection;

public interface OrderStatusService {
    Collection<OrderStatusReadDto> findAll();
}
