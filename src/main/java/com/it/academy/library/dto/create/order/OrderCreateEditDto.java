package com.it.academy.library.dto.create.order;

import com.it.academy.library.model.entity.order.Order;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link Order} entity.
 */
@Value
public class OrderCreateEditDto {
    Long userId;

    Integer orderStatusId;

    Integer orderTypeId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime orderDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime returnDate;
}
