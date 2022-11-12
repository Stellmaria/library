package com.it.academy.library.service.dto.create.order;

import com.it.academy.library.model.entity.order.OrderStatus;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link OrderStatus} entity.
 */
@Value
public class OrderStatusCreateEditDto {
    @NotBlank
    @Size(min = 3, max = 64)
    String name;
}
