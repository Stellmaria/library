package com.it.academy.library.model.repository.filter.order.impl;

import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.model.repository.filter.order.FilterOrderStatusRepository;
import com.it.academy.library.service.dto.filter.order.OrderStatusFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.order.QOrderStatus.orderStatus;

@RequiredArgsConstructor
public class FilterOrderStatusRepositoryImpl implements FilterOrderStatusRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<OrderStatus> findAllByOrderStatusFilter(@NotNull OrderStatusFilter orderStatusFilter) {
        return new JPAQuery<OrderStatus>(entityManager)
                .select(orderStatus)
                .from(orderStatus)
                .where(OrderStatusFilter.queryPredicates(orderStatusFilter))
                .fetch();
    }
}
