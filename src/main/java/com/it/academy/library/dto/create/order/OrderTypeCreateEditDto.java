package com.it.academy.library.dto.create.order;

import com.it.academy.library.model.entity.order.OrderType;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link OrderType} entity.
 */
@Value
public class OrderTypeCreateEditDto {
    @NotBlank
    @Size(min = 3, max = 64)
    String name;
}
