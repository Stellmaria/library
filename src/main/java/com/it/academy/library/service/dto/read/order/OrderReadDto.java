package com.it.academy.library.service.dto.read.order;

import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.service.dto.read.user.UserReadDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link Order} entity.
 */
@Value
@AllArgsConstructor
@Builder
public class OrderReadDto {
    Long id;

    UserReadDto user;

    OrderStatusReadDto orderStatus;

    LocalDateTime orderDate;

    LocalDateTime returnDate;
}
