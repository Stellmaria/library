package com.it.academy.library.model.mapper.filter.order;

import com.it.academy.library.dto.filter.order.OrderFilter;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrderFilterMapper implements Mapper<Order, OrderFilter> {
    @Override
    public OrderFilter map(@NotNull Order object) {
        return new OrderFilter(
                object.getUser() != null ? object.getUser().getId() : null,
                object.getOrderStatus() != null ? object.getOrderStatus().getId() : null,
                object.getOrderType() != null ? object.getOrderType().getId() : null,
                object.getOrderDate(),
                object.getReturnDate()
        );
    }
}
