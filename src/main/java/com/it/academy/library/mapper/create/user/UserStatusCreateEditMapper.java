package com.it.academy.library.mapper.create.user;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.service.dto.create.user.UserStatusCreateEditDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserStatusCreateEditMapper implements Mapper<UserStatusCreateEditDto, UserStatus> {
    @Override
    public UserStatus map(@NotNull UserStatusCreateEditDto object) {
        var userStatus = new UserStatus();

        userStatus.setName(object.getName());

        return userStatus;
    }
}
