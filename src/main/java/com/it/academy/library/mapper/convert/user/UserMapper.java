package com.it.academy.library.mapper.convert.user;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.User;
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
        var user = new User();

        user.setId(object.getId());
        user.setUsername(object.getUsername());
        user.setFirstName(object.getFirstName());
        user.setLastName(object.getLastName());
        user.setEmail(object.getEmail());
        user.setPassword(object.getPassword());
        user.setUserRole(Optional.of(object.getUserRole())
                .map(userRoleMapper::map)
                .orElse(null)
        );
        user.setUserStatus(Optional.of(object.getUserStatus())
                .map(userStatusMapper::map)
                .orElse(null)
        );
        user.setBirthday(object.getBirthday());
        user.setImage(object.getImage());

        return user;
    }
}
