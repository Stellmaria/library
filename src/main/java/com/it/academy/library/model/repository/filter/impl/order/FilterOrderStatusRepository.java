package com.it.academy.library.model.repository.filter.impl.order;

import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.service.dto.filter.order.OrderStatusFilter;

import java.util.Collection;

public interface FilterOrderStatusRepository {
    /**
     * Search for order statuses by order status filter.
     *
     * @param orderStatusFilter filter.
     * @return orders status.
     */
    Collection<OrderStatus> findAllByOrderStatusFilter(OrderStatusFilter orderStatusFilter);
}
