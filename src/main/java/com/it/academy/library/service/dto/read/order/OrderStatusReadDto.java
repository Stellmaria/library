package com.it.academy.library.service.dto.read.order;

import com.it.academy.library.model.entity.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * A DTO for the {@link OrderStatus} entity.
 */
@Data
@AllArgsConstructor
@Builder
public class OrderStatusReadDto {
    private Integer id;

    private String name;
}
