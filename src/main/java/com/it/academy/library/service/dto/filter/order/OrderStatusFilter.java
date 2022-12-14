package com.it.academy.library.service.dto.filter.order;

import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.order.QOrderStatus.orderStatus;

/**
 * A DTO for the {@link OrderStatus} entity.
 */
@Getter
@AllArgsConstructor
public class OrderStatusFilter {
    private Integer id;

    private String name;

    public static Predicate queryPredicates(@NotNull OrderStatusFilter orderStatusFilter) {
        return QueryPredicates.builder()
                .add(orderStatusFilter.getId(), orderStatus.id::eq)
                .add(orderStatusFilter.getName(), orderStatus.name::containsIgnoreCase)
                .build();
    }
}
