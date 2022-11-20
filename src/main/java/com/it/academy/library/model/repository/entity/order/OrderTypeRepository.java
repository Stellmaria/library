package com.it.academy.library.model.repository.entity.order;

import com.it.academy.library.model.entity.order.OrderType;
import com.it.academy.library.model.repository.filter.impl.order.FilterOrderTypeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderTypeRepository extends
        JpaRepository<OrderType, Integer>,
        FilterOrderTypeRepository,
        QuerydslPredicateExecutor<OrderType> {
}
