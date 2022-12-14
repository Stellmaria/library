package com.it.academy.library.mapper.convert.user;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.service.dto.read.user.UserRoleReadDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper implements Mapper<UserRoleReadDto, UserRole> {
    @Override
    public UserRole map(@NotNull UserRoleReadDto object) {
        return UserRole.builder()
                .id(object.getId())
                .name(object.getName())
                .build();
    }
}
