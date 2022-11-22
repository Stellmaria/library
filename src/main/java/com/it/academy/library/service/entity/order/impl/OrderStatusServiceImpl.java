package com.it.academy.library.service.entity.order.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.read.order.OrderStatusReadMapper;
import com.it.academy.library.model.repository.entity.order.OrderStatusRepository;
import com.it.academy.library.service.dto.read.order.OrderStatusReadDto;
import com.it.academy.library.service.entity.order.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    private final OrderStatusReadMapper orderStatusReadMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Collection<OrderStatusReadDto> findAll() {
        return orderStatusRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return orderStatusReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }
}
