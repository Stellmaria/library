package com.it.academy.library.service.entity.order;

import com.it.academy.library.service.dto.create.OrderCreateEditDto;
import com.it.academy.library.service.dto.filter.order.OrderFilter;
import com.it.academy.library.service.dto.read.order.OrderReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface OrderService {

    OrderReadDto create(OrderCreateEditDto dto);

    Optional<OrderReadDto> findById(Long id);

    Collection<OrderReadDto> findAll();

    Page<OrderReadDto> findAll(OrderFilter filter, Pageable pageable);


    Optional<OrderReadDto> update(Long id, OrderCreateEditDto dto);

    boolean delete(Long id);
}
