package com.it.academy.library.service.dto.read.order;

import com.it.academy.library.model.entity.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link OrderStatus} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusReadDto {
    private Integer id;

    private String name;
}
