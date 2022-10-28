package com.it.academy.library.model.mapper.filter.order;

import com.it.academy.library.dto.filter.order.OrderStatusFilter;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusFilterMapper implements Mapper<OrderStatus, OrderStatusFilter> {
    @Override
    public OrderStatusFilter map(@NotNull OrderStatus object) {
        return new OrderStatusFilter(object.getName());
    }
}
