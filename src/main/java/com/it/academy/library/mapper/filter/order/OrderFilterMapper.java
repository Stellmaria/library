package com.it.academy.library.mapper.filter.order;

import com.it.academy.library.dto.filter.order.OrderFilter;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.mapper.filter.user.UserFilterMapper;
import com.it.academy.library.model.entity.order.Order;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderFilterMapper implements Mapper<Order, OrderFilter> {
    private final UserFilterMapper userFilterMapper;
    private final OrderStatusFilterMapper orderStatusFilterMapper;
    private final OrderTypeFilterMapper orderTypeFilterMapper;

    @Override
    public OrderFilter map(@NotNull Order object) {
        var user = Optional.ofNullable(object.getUser())
                .map(userFilterMapper::map)
                .orElse(null);
        var orderStatus = Optional.ofNullable(object.getOrderStatus())
                .map(orderStatusFilterMapper::map)
                .orElse(null);
        var orderType = Optional.ofNullable(object.getOrderType())
                .map(orderTypeFilterMapper::map)
                .orElse(null);

        return new OrderFilter(
                object.getId(),
                user,
                orderStatus,
                orderType,
                object.getOrderDate(),
                object.getReturnDate()
        );
    }
}
