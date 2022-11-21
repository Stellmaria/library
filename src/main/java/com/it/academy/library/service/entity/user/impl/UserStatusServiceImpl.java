package com.it.academy.library.service.entity.user.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.read.user.UserStatusReadMapper;
import com.it.academy.library.model.repository.entity.user.UserStatusRepository;
import com.it.academy.library.service.dto.read.user.UserStatusReadDto;
import com.it.academy.library.service.entity.user.UserStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserStatusServiceImpl implements UserStatusService {
    private final UserStatusRepository userStatusRepository;

    private final UserStatusReadMapper userStatusReadMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Collection<UserStatusReadDto> findAll() {
        return userStatusRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return userStatusReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }
}
