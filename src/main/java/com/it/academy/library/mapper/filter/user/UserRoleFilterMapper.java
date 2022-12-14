package com.it.academy.library.mapper.filter.user;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.service.dto.filter.user.UserRoleFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserRoleFilterMapper implements Mapper<UserRole, UserRoleFilter> {
    @Override
    public UserRoleFilter map(@NotNull UserRole object) {
        return new UserRoleFilter(
                object.getId(),
                object.getName()
        );
    }
}
