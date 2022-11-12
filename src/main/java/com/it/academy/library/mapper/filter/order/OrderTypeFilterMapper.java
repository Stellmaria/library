package com.it.academy.library.mapper.filter.order;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.order.OrderType;
import com.it.academy.library.service.dto.filter.order.OrderTypeFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrderTypeFilterMapper implements Mapper<OrderType, OrderTypeFilter> {
    @Override
    public OrderTypeFilter map(@NotNull OrderType object) {
        return new OrderTypeFilter(
                object.getId(),
                object.getName()
        );
    }
}
