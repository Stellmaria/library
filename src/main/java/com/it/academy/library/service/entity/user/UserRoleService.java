package com.it.academy.library.service.entity.user;

import com.it.academy.library.mapper.read.user.UserRoleReadMapper;
import com.it.academy.library.model.repository.entity.user.UserRoleRepository;
import com.it.academy.library.service.dto.read.user.UserRoleReadDto;
import lombok.RequiredArgsConstructor;
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

    public Optional<UserRoleReadDto> findById(Integer id) {
        return userRoleRepository.findById(id)
                .map(userRoleReadMapper::map);
    }

    public Collection<UserRoleReadDto> findAll() {
        return userRoleRepository.findAll().stream()
                .map(userRoleReadMapper::map)
                .collect(Collectors.toList());
    }
}
