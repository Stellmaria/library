package com.it.academy.library.model.repository.entity.order;

import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.model.repository.filter.impl.order.FilterOrderStatusRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderStatusRepository extends
        JpaRepository<OrderStatus, Integer>,
        FilterOrderStatusRepository,
        QuerydslPredicateExecutor<OrderStatus> {
}
