package com.it.academy.library.mapper.read.user;

import com.it.academy.library.dto.read.user.UserStatusReadDto;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.UserStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserStatusReadMapper implements Mapper<UserStatus, UserStatusReadDto> {
    @Override
    public UserStatusReadDto map(@NotNull UserStatus object) {
        return new UserStatusReadDto(
                object.getId(),
                object.getName()
        );
    }
}
