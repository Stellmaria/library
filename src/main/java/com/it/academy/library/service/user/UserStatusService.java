package com.it.academy.library.service.user;

import com.it.academy.library.dto.read.user.UserStatusReadDto;
import com.it.academy.library.model.listener.entity.AccessType;
import com.it.academy.library.model.listener.entity.EntityEvent;
import com.it.academy.library.model.mapper.read.user.UserStatusReadMapper;
import com.it.academy.library.model.repository.entity.user.UserStatusRepository;
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
public class UserStatusService {
    private final UserStatusRepository userStatusRepository;
    private final UserStatusReadMapper userStatusReadMapper;
    private final ApplicationEventPublisher eventPublisher;

    public Optional<UserStatusReadDto> findById(Integer id) {
        return userStatusRepository.findById(id)
                .map(it -> {
                    eventPublisher.publishEvent(new EntityEvent(it, AccessType.READ));
                    return userStatusReadMapper.map(it);
                });
    }

    public Collection<UserStatusReadDto> findAll() {
        return userStatusRepository.findAll().stream()
                .map(it -> {
                    eventPublisher.publishEvent(new EntityEvent(it, AccessType.READ));
                    return userStatusReadMapper.map(it);
                })
                .collect(Collectors.toList());
    }
}
