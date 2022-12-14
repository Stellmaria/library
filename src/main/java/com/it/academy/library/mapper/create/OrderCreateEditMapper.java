package com.it.academy.library.mapper.create;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.repository.entity.order.OrderStatusRepository;
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

    @Override
    public Order map(@NotNull OrderCreateEditDto fromObject, @NotNull Order toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    private void copy(@NotNull OrderCreateEditDto object, @NotNull Order order) {
        order.setUser(getUser(object.getUserId()));
        order.setOrderStatus(getOrderStatus(object.getOrderStatusId()));
        order.setOrderDate(object.getOrderDate());
        order.setReturnDate(object.getReturnDate());
    }

    private User getUser(Long id) {
        return Optional.ofNullable(id)
                .flatMap(userRepository::findById)
                .orElseThrow();
    }

    private OrderStatus getOrderStatus(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(orderStatusRepository::findById)
                .orElseThrow();
    }
}
