package com.it.academy.library.service.dto.read.order;

import com.it.academy.library.model.entity.order.OrderType;
import lombok.Value;

/**
 * A DTO for the {@link OrderType} entity.
 */
@Value
public class OrderTypeReadDto {
    Integer id;

    String name;
}
