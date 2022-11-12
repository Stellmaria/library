package com.it.academy.library.mapper.read.order;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.mapper.read.user.UserReadMapper;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.service.dto.read.order.OrderReadDto;
import com.it.academy.library.service.dto.read.order.OrderStatusReadDto;
import com.it.academy.library.service.dto.read.order.OrderTypeReadDto;
import com.it.academy.library.service.dto.read.user.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderReadMapper implements Mapper<Order, OrderReadDto> {
    private final UserReadMapper userReadMapper;
    private final OrderStatusReadMapper orderStatusReadMapper;
    private final OrderTypeReadMapper orderTypeReadMapper;

    @Override
    public OrderReadDto map(@NotNull Order object) {
        return new OrderReadDto(
                object.getId(),
                getUser(object),
                getOrderStatus(object),
                getOrderType(object),
                object.getOrderDate(),
                object.getReturnDate()
        );
    }

    private OrderStatusReadDto getOrderStatus(@NotNull Order object) {
        return Optional.ofNullable(object.getOrderStatus())
                .map(orderStatusReadMapper::map)
                .orElse(null);
    }

    private OrderTypeReadDto getOrderType(@NotNull Order object) {
        return Optional.ofNullable(object.getOrderType())
                .map(orderTypeReadMapper::map)
                .orElse(null);
    }

    private UserReadDto getUser(@NotNull Order object) {
        return Optional.ofNullable(object.getUser())
                .map(userReadMapper::map)
                .orElse(null);
    }
}
