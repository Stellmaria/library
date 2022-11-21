package com.it.academy.library.service.entity.user.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.read.user.UserRoleReadMapper;
import com.it.academy.library.model.repository.entity.user.UserRoleRepository;
import com.it.academy.library.service.dto.read.user.UserRoleReadDto;
import com.it.academy.library.service.entity.user.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    private final UserRoleReadMapper userRoleReadMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Collection<UserRoleReadDto> findAll() {
        return userRoleRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return userRoleReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }
}
