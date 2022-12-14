package com.it.academy.library.mapper.filter.user;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.service.dto.filter.user.UserStatusFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserStatusFilterMapper implements Mapper<UserStatus, UserStatusFilter> {
    @Override
    public UserStatusFilter map(@NotNull UserStatus object) {
        return new UserStatusFilter(
                object.getId(),
                object.getName()
        );
    }
}
