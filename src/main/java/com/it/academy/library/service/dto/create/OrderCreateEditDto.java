package com.it.academy.library.service.dto.create;

import com.it.academy.library.model.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link Order} entity.
 */
@AllArgsConstructor
@Getter
public class OrderCreateEditDto {
    @NotNull
    private Long userId;

    @NotNull
    private Integer orderStatusId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime returnDate;

    @AssertTrue(message = "Return date must not be before order date.")
    public boolean isDateRangeValid() {
        return orderDate == null || returnDate == null || !returnDate.isBefore(orderDate);
    }
}
