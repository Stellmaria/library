package com.it.academy.library.mapper.read.order;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.service.dto.read.order.OrderStatusReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusReadMapper implements Mapper<OrderStatus, OrderStatusReadDto> {
    @Override
    public OrderStatusReadDto map(@NotNull OrderStatus object) {
        return new OrderStatusReadDto(
                object.getId(),
                object.getName()
        );
    }
}
