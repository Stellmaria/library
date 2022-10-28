package com.it.academy.library.model.mapper.read.order;

import com.it.academy.library.dto.read.order.OrderTypeReadDto;
import com.it.academy.library.model.entity.order.OrderType;
import com.it.academy.library.model.mapper.Mapper;
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
