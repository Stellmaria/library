package com.it.academy.library.mapper.create.order;

import com.it.academy.library.dto.create.order.OrderStatusCreateEditDto;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.order.OrderStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusCreateEditMapper implements Mapper<OrderStatusCreateEditDto, OrderStatus> {
    @Override
    public OrderStatus map(@NotNull OrderStatusCreateEditDto object) {
        var orderStatus = new OrderStatus();
        orderStatus.setName(object.getName());
        return orderStatus;
    }
}
