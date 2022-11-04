package com.it.academy.library.dto.filter.order;

import com.it.academy.library.model.entity.order.OrderType;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.order.QOrderType.orderType;

/**
 * A DTO for the {@link OrderType} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderTypeFilter {
    private Integer id;

    private String name;

    public static Predicate queryPredicates(@NotNull OrderTypeFilter orderTypeFilter) {
        return QueryPredicates.builder()
                .add(orderTypeFilter.getId(), orderType.id::eq)
                .add(orderTypeFilter.getName(), orderType.name::containsIgnoreCase)
                .build();
    }
}
