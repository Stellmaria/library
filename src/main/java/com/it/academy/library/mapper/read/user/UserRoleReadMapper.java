package com.it.academy.library.mapper.read.user;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.service.dto.read.user.UserRoleReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserRoleReadMapper implements Mapper<UserRole, UserRoleReadDto> {
    @Override
    public UserRoleReadDto map(@NotNull UserRole object) {
        return new UserRoleReadDto(
                object.getId(),
                object.getName()
        );
    }
}
