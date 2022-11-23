package com.it.academy.library.mapper.convert.order;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.mapper.convert.user.UserMapper;
import com.it.academy.library.model.entity.order.Order;
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
        var order = new Order();

        order.setId(object.getId());
        order.setUser(Optional.ofNullable(object.getUser())
                .map(userMapper::map)
                .orElse(null)
        );
        order.setOrderStatus(Optional.ofNullable(object.getOrderStatus())
                .map(orderStatusMapper::map)
                .orElse(null)
        );
        order.setOrderDate(order.getOrderDate());
        order.setReturnDate(object.getReturnDate());

        return order;
    }
}
