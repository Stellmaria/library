package com.it.academy.library.service.user;

import com.it.academy.library.dto.read.user.UserRoleReadDto;
import com.it.academy.library.model.listener.entity.AccessType;
import com.it.academy.library.model.listener.entity.EntityEvent;
import com.it.academy.library.model.mapper.read.user.UserRoleReadMapper;
import com.it.academy.library.model.repository.entity.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRoleReadMapper userRoleReadMapper;
    private final ApplicationEventPublisher eventPublisher;

    public Optional<UserRoleReadDto> findById(Integer id) {
        return userRoleRepository.findById(id)
                .map(it -> {
                    eventPublisher.publishEvent(new EntityEvent(it, AccessType.READ));
                    return userRoleReadMapper.map(it);
                });
    }

    public Collection<UserRoleReadDto> findAll() {
        return userRoleRepository.findAll().stream()
                .map(it -> {
                    eventPublisher.publishEvent(new EntityEvent(it, AccessType.READ));
                    return userRoleReadMapper.map(it);
                })
                .collect(Collectors.toList());
    }
}
