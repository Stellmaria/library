package com.it.academy.library.mapper.convert.user;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.service.dto.read.user.UserStatusReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserStatusMapper implements Mapper<UserStatusReadDto, UserStatus> {
    @Override
    public UserStatus map(@NotNull UserStatusReadDto object) {
        return UserStatus.builder()
                .id(object.getId())
                .name(object.getName())
                .build();
    }
}