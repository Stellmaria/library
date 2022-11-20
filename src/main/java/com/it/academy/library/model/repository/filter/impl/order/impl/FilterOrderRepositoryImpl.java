package com.it.academy.library.model.repository.filter.impl.order.impl;

import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.repository.filter.impl.order.FilterOrderRepository;
import com.it.academy.library.service.dto.filter.order.OrderFilter;
import com.it.academy.library.service.dto.filter.order.OrderStatusFilter;
import com.it.academy.library.service.dto.filter.order.OrderTypeFilter;
import com.it.academy.library.service.dto.filter.user.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.order.QOrder.order;

@RequiredArgsConstructor
public class FilterOrderRepositoryImpl implements FilterOrderRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<Order> findAllByOrderFilter(@NotNull OrderFilter orderFilter) {
        return new JPAQuery<Order>(entityManager)
                .select(order)
                .from(order)
                .where(OrderFilter.queryPredicates(orderFilter))
                .fetch();
    }

    @Override
    public Collection<Order> findAllByUserFilter(@NotNull UserFilter userFilter) {
        return new JPAQuery<Order>(entityManager)
                .select(order)
                .from(order)
                .where(UserFilter.queryPredicates(userFilter))
                .fetch();
    }

    @Override
    public Collection<Order> findAllByOrderStatusFilter(@NotNull OrderStatusFilter orderStatusFilter) {
        return new JPAQuery<Order>(entityManager)
                .select(order)
                .from(order)
                .where(OrderStatusFilter.queryPredicates(orderStatusFilter))
                .fetch();
    }

    @Override
    public Collection<Order> findAllByOrderTypeFilter(@NotNull OrderTypeFilter orderTypeFilter) {
        return new JPAQuery<Order>(entityManager)
                .select(order)
                .from(order)
                .where(OrderTypeFilter.queryPredicates(orderTypeFilter))
                .fetch();
    }
}
