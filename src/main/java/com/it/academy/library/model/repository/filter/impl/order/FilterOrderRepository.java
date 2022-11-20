package com.it.academy.library.model.repository.filter.impl.order;

import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.service.dto.filter.order.OrderFilter;
import com.it.academy.library.service.dto.filter.order.OrderStatusFilter;
import com.it.academy.library.service.dto.filter.order.OrderTypeFilter;
import com.it.academy.library.service.dto.filter.user.UserFilter;

import java.util.Collection;

public interface FilterOrderRepository {
    /**
     * Search orders by order filter.
     *
     * @param orderFilter filter.
     * @return orders.
     */
    Collection<Order> findAllByOrderFilter(OrderFilter orderFilter);

    /**
     * Search orders by user filter.
     *
     * @param userFilter filter.
     * @return orders.
     */
    Collection<Order> findAllByUserFilter(UserFilter userFilter);

    /**
     * Search orders by order status filter.
     *
     * @param orderStatusFilter filter.
     * @return orders.
     */
    Collection<Order> findAllByOrderStatusFilter(OrderStatusFilter orderStatusFilter);

    /**
     * Search orders by order type filter.
     *
     * @param orderTypeFilter filter.
     * @return orders.
     */
    Collection<Order> findAllByOrderTypeFilter(OrderTypeFilter orderTypeFilter);
}
