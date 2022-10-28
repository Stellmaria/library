package com.it.academy.library.model.mapper.read.user;

import com.it.academy.library.dto.read.user.UserReadDto;
import com.it.academy.library.dto.read.user.UserRoleReadDto;
import com.it.academy.library.dto.read.user.UserStatusReadDto;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {
    private final UserRoleReadMapper userRoleReadMapper;
    private final UserStatusReadMapper userStatusReadMapper;

    @Override
    public UserReadDto map(@NotNull User object) {
        return new UserReadDto(
                object.getId(),
                object.getLogin(),
                object.getFirstName(),
                object.getLastName(),
                object.getEmail(),
                object.getPassword(),
                getUserRole(object),
                getUserStatus(object),
                object.getBirthday()
        );
    }

    private UserStatusReadDto getUserStatus(@NotNull User object) {
        return Optional.ofNullable(object.getUserStatus())
                .map(userStatusReadMapper::map)
                .orElse(null);
    }

    private UserRoleReadDto getUserRole(@NotNull User object) {
        return Optional.ofNullable(object.getUserRole())
                .map(userRoleReadMapper::map)
                .orElse(null);
    }
}
