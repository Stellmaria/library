package com.it.academy.library.model.mapper.create.user;

import com.it.academy.library.dto.create.user.UserStatusCreateEditDto;
import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.model.mapper.Mapper;
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
