package com.it.academy.library.mapper.convert.order;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.mapper.convert.user.UserMapper;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.service.dto.read.order.OrderReadDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class OrderMapper implements Mapper<OrderReadDto, Order> {
    private final UserMapper userMapper;
    private final OrderStatusMapper orderStatusMapper;

    @Override
    public Order map(@NotNull OrderReadDto object) {
        return Order.builder()
                .id(object.getId())
                .user(getUser(object))
                .orderStatus(getOrderStatus(object))
                .orderDate(object.getOrderDate())
                .returnDate(object.getReturnDate())
                .build();
    }

    private User getUser(@NotNull OrderReadDto object) {
        return Optional.ofNullable(object.getUser())
                .map(userMapper::map)
                .orElse(null);
    }

    private OrderStatus getOrderStatus(@NotNull OrderReadDto object) {
        return Optional.ofNullable(object.getOrderStatus())
                .map(orderStatusMapper::map)
                .orElse(null);
    }
}
