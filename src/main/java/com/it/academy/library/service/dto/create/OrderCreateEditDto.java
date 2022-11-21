package com.it.academy.library.service.dto.create;

import com.it.academy.library.model.entity.order.Order;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link Order} entity.
 */
@Value
public class OrderCreateEditDto {
    @NotBlank
    @Positive
    Long userId;

    @NotBlank
    @Positive
    Integer orderStatusId;

    @NotBlank
    @Positive
    Integer orderTypeId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotBlank
    LocalDateTime orderDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotBlank
    LocalDateTime returnDate;
}
