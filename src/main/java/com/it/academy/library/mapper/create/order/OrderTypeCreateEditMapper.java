package com.it.academy.library.mapper.create.order;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.order.OrderType;
import com.it.academy.library.service.dto.create.order.OrderTypeCreateEditDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrderTypeCreateEditMapper implements Mapper<OrderTypeCreateEditDto, OrderType> {
    @Override
    public OrderType map(@NotNull OrderTypeCreateEditDto object) {
        var orderType = new OrderType();
        orderType.setName(object.getName());

        return orderType;
    }
}
