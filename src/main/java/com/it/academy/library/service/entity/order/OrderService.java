package com.it.academy.library.service.entity.order;

import com.it.academy.library.service.dto.create.OrderCreateEditDto;
import com.it.academy.library.service.dto.filter.order.OrderFilter;
import com.it.academy.library.service.dto.read.order.OrderReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface OrderService {
    /**
     * Creating a new order.
     *
     * @param dto for creating.
     * @return new order.
     */
    OrderReadDto create(OrderCreateEditDto dto);

    /**
     * Search for an order by order id.
     *
     * @param id for search.
     * @return order.
     */
    Optional<OrderReadDto> findById(Long id);

    /**
     * Search for all orders.
     *
     * @return collection of found orders.
     */
    Collection<OrderReadDto> findAll();

    /**
     * Search for all orders with filtering.
     *
     * @param filter   for search.
     * @param pageable for search.
     * @return collection of found orders.
     */
    Page<OrderReadDto> findAll(OrderFilter filter, Pageable pageable);

    /**
     * Updating an order by order ID.
     *
     * @param id  for an update.
     * @param dto for an update.
     * @return updated order.
     */
    Optional<OrderReadDto> update(Long id, OrderCreateEditDto dto);

    /**
     * Deleting an order by order id.
     *
     * @param id for removing.
     * @return true - succeeded; false - failed.
     */
    boolean delete(Long id);
}
