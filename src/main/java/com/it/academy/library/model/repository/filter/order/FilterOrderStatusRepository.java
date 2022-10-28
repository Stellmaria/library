package com.it.academy.library.model.repository.filter.order;

import com.it.academy.library.dto.filter.order.OrderStatusFilter;
import com.it.academy.library.model.entity.order.OrderStatus;

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
