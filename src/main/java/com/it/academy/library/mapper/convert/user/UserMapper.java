package com.it.academy.library.mapper.convert.user;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.service.dto.read.user.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserMapper implements Mapper<UserReadDto, User> {
    public final UserStatusMapper userStatusMapper;
    private final UserRoleMapper userRoleMapper;

    @Override
    public User map(@NotNull UserReadDto object) {
        return User.builder()
                .id(object.getId())
                .username(object.getUsername())
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .email(object.getEmail())
                .password(object.getPassword())
                .userRole(getUserRole(object))
                .userStatus(getUserStatus(object))
                .birthday(object.getBirthday())
                .image(object.getImage())
                .build();
    }

    private UserStatus getUserStatus(@NotNull UserReadDto object) {
        return Optional.of(object.getUserStatus())
                .map(userStatusMapper::map)
                .orElse(null);
    }

    private UserRole getUserRole(@NotNull UserReadDto object) {
        return Optional.of(object.getUserRole())
                .map(userRoleMapper::map)
                .orElse(null);
    }
}
