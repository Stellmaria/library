package com.it.academy.library.service.dto.create;

import com.it.academy.library.model.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link Order} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateEditDto {
    private Long userId;

    private Integer orderStatusId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime returnDate;
}
