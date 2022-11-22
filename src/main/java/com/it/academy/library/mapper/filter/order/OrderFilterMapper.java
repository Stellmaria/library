package com.it.academy.library.mapper.filter.order;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.mapper.filter.user.UserFilterMapper;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.service.dto.filter.order.OrderFilter;
import com.it.academy.library.service.dto.filter.order.OrderStatusFilter;
import com.it.academy.library.service.dto.filter.user.UserFilter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderFilterMapper implements Mapper<Order, OrderFilter> {
    private final UserFilterMapper userFilterMapper;
    private final OrderStatusFilterMapper orderStatusFilterMapper;

    @Override
    public OrderFilter map(@NotNull Order object) {
        return new OrderFilter(
                object.getId(),
                getUser(object),
                getOrderStatus(object),
                object.getOrderDate(),
                object.getReturnDate()
        );
    }

    @Nullable
    private OrderStatusFilter getOrderStatus(@NotNull Order object) {
        return Optional.ofNullable(object.getOrderStatus())
                .map(orderStatusFilterMapper::map)
                .orElse(null);
    }

    @Nullable
    private UserFilter getUser(@NotNull Order object) {
        return Optional.ofNullable(object.getUser())
                .map(userFilterMapper::map)
                .orElse(null);
    }
}
