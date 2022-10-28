package com.it.academy.library.model.mapper.read.order;

import com.it.academy.library.dto.read.order.OrderStatusReadDto;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.model.mapper.Mapper;
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
