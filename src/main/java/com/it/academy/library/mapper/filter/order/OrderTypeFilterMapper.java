package com.it.academy.library.mapper.filter.order;

import com.it.academy.library.dto.filter.order.OrderTypeFilter;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.order.OrderType;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrderTypeFilterMapper implements Mapper<OrderType, OrderTypeFilter> {
    @Override
    public OrderTypeFilter map(@NotNull OrderType object) {
        return new OrderTypeFilter(object.getName());
    }
}
