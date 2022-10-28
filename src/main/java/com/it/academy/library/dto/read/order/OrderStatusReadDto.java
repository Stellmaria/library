package com.it.academy.library.dto.read.order;

import com.it.academy.library.model.entity.order.OrderStatus;
import lombok.Value;

/**
 * A DTO for the {@link OrderStatus} entity.
 */
@Value
public class OrderStatusReadDto {
    Integer id;

    String name;
}
