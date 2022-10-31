package com.it.academy.library.dto.filter.order;

import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

import static com.it.academy.library.model.entity.order.QOrder.order;

/**
 * A DTO for the {@link Order} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderFilter {
    private Long userId;

    private Integer orderStatusId;

    private Integer orderTypeId;

    private LocalDateTime orderDate;

    private LocalDateTime returnDate;

    public static Predicate queryPredicates(@NotNull OrderFilter orderFilter) {
        return QueryPredicates.builder()
                .add(orderFilter.getUserId(), order.user.id::eq)
                .add(orderFilter.getOrderStatusId(), order.orderStatus.id::eq)
                .add(orderFilter.getOrderTypeId(), order.orderType.id::eq)
                .add(orderFilter.getOrderDate(), order.orderDate::eq)
                .add(orderFilter.getReturnDate(), order.returnDate::eq)
                .build();
    }
}
