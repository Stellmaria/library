package com.it.academy.library.mapper.filter.order;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.service.dto.filter.order.OrderStatusFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusFilterMapper implements Mapper<OrderStatus, OrderStatusFilter> {
    @Override
    public OrderStatusFilter map(@NotNull OrderStatus object) {
        return new OrderStatusFilter(
                object.getId(),
                object.getName()
        );
    }
}
