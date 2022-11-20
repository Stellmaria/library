package com.it.academy.library.mapper.create;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.model.entity.order.OrderType;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.repository.entity.order.OrderStatusRepository;
import com.it.academy.library.model.repository.entity.order.OrderTypeRepository;
import com.it.academy.library.model.repository.entity.user.UserRepository;
import com.it.academy.library.service.dto.create.OrderCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderCreateEditMapper implements Mapper<OrderCreateEditDto, Order> {
    private final UserRepository userRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderTypeRepository orderTypeRepository;

    @Override
    public Order map(@NotNull OrderCreateEditDto fromObject, @NotNull Order toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    @Override
    public Order map(@NotNull OrderCreateEditDto object) {
        var order = new Order();

        copy(object, order);

        return order;
    }

    private void copy(@NotNull OrderCreateEditDto object, @NotNull Order order) {
        order.setUser(getUser(object.getUserId()));
        order.setOrderStatus(getOrderStatus(object.getOrderStatusId()));
        order.setOrderType(getOrderType(object.getOrderTypeId()));
        order.setOrderDate(object.getOrderDate());
        order.setReturnDate(object.getReturnDate());
    }

    private User getUser(Long id) {
        return Optional.ofNullable(id)
                .flatMap(userRepository::findById)
                .orElse(null);
    }

    private OrderStatus getOrderStatus(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(orderStatusRepository::findById)
                .orElse(null);
    }

    private OrderType getOrderType(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(orderTypeRepository::findById)
                .orElse(null);
    }
}
