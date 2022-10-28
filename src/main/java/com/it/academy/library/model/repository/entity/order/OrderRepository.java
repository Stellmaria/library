package com.it.academy.library.model.repository.entity.order;

import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.repository.filter.order.FilterOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderRepository extends
        JpaRepository<Order, Long>,
        FilterOrderRepository,
        QuerydslPredicateExecutor<Order> {
}
