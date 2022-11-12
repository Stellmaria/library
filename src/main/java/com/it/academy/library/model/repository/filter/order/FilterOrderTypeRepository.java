package com.it.academy.library.model.repository.filter.order;

import com.it.academy.library.model.entity.order.OrderType;
import com.it.academy.library.service.dto.filter.order.OrderTypeFilter;

import java.util.Collection;

public interface FilterOrderTypeRepository {
    /**
     * Search for order types by order type filter.
     *
     * @param orderTypeFilter filter.
     * @return order types.
     */
    Collection<OrderType> findAllByOrderTypeFilter(OrderTypeFilter orderTypeFilter);
}
