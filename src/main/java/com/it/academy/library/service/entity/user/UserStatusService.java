package com.it.academy.library.service.entity.user;

import com.it.academy.library.mapper.read.user.UserStatusReadMapper;
import com.it.academy.library.model.repository.entity.user.UserStatusRepository;
import com.it.academy.library.service.dto.read.user.UserStatusReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserStatusService {
    private final UserStatusRepository userStatusRepository;
    private final UserStatusReadMapper userStatusReadMapper;

    public Collection<UserStatusReadDto> findAll() {
        return userStatusRepository.findAll().stream()
                .map(userStatusReadMapper::map)
                .collect(Collectors.toList());
    }
}
