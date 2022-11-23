package com.it.academy.library.mapper.convert.order;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.service.dto.read.order.OrderStatusReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusMapper implements Mapper<OrderStatusReadDto, OrderStatus> {
    @Override
    public OrderStatus map(@NotNull OrderStatusReadDto object) {
        var orderStatus = new OrderStatus();

        orderStatus.setId(object.getId());
        orderStatus.setName(object.getName());

        return orderStatus;
    }
}
