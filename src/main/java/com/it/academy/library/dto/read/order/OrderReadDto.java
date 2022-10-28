package com.it.academy.library.dto.read.order;

import com.it.academy.library.dto.read.user.UserReadDto;
import com.it.academy.library.model.entity.order.Order;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link Order} entity.
 */
@Value
public class OrderReadDto {
    Long id;

    UserReadDto user;

    OrderStatusReadDto orderStatus;

    OrderTypeReadDto orderType;

    LocalDateTime orderDate;

    LocalDateTime returnDate;
}
