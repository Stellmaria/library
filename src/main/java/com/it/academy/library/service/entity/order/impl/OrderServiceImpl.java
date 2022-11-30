package com.it.academy.library.service.entity.order.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.create.OrderCreateEditMapper;
import com.it.academy.library.mapper.read.order.OrderReadMapper;
import com.it.academy.library.model.repository.entity.order.OrderRepository;
import com.it.academy.library.service.dto.create.OrderCreateEditDto;
import com.it.academy.library.service.dto.filter.order.OrderFilter;
import com.it.academy.library.service.dto.filter.user.UserFilter;
import com.it.academy.library.service.dto.read.order.OrderReadDto;
import com.it.academy.library.service.entity.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final ApplicationEventPublisher eventPublisher;

    private final OrderCreateEditMapper orderCreateEditMapper;
    private final OrderReadMapper orderReadMapper;

    @Override
    public Optional<OrderReadDto> findById(Long id) {
        return orderRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return orderReadMapper.map(entity);
                });
    }

    @Override
    public Collection<OrderReadDto> findAll() {
        return orderRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return orderReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Page<OrderReadDto> findAll(OrderFilter filter, Pageable pageable) {
        return orderRepository.findAll(OrderFilter.queryPredicates(filter), pageable)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return orderReadMapper.map(entity);
                });
    }

    @Override
    public Collection<OrderReadDto> findByUserId(Long id) {
        var filer = new UserFilter();
        filer.setId(id);

        return orderRepository.findAllByUserFilter(filer).stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return orderReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Optional<OrderReadDto> update(Long id, OrderCreateEditDto dto) {
        return orderRepository.findById(id)
                .map(entity -> orderCreateEditMapper.map(dto, entity))
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.UPDATE));

                    return orderRepository.saveAndFlush(entity);
                })
                .map(orderReadMapper::map);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean delete(Long id) {
        return orderRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.DELETE));

                    orderRepository.delete(entity);
                    orderRepository.flush();

                    return true;
                })
                .orElse(false);
    }
}
