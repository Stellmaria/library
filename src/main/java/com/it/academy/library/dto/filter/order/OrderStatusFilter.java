package com.it.academy.library.dto.filter.order;

import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.order.QOrderStatus.orderStatus;

/**
 * A DTO for the {@link OrderStatus} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusFilter {
    private String name;

    public static Predicate queryPredicates(@NotNull OrderStatusFilter orderStatusFilter) {
        return QueryPredicates.builder()
                .add(orderStatusFilter.getName(), orderStatus.name::containsIgnoreCase)
                .build();
    }
}
