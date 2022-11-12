package com.it.academy.library.mapper.read.order;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.order.OrderType;
import com.it.academy.library.service.dto.read.order.OrderTypeReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrderTypeReadMapper implements Mapper<OrderType, OrderTypeReadDto> {
    @Override
    public OrderTypeReadDto map(@NotNull OrderType object) {
        return new OrderTypeReadDto(
                object.getId(),
                object.getName()
        );
    }
}
