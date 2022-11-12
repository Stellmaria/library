package com.it.academy.library.mapper.create.user;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.service.dto.create.user.UserRoleCreateEditDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserRoleCreateEditMapper implements Mapper<UserRoleCreateEditDto, UserRole> {
    @Override
    public UserRole map(@NotNull UserRoleCreateEditDto object) {
        var userRole = new UserRole();
        userRole.setName(object.getName());

        return userRole;
    }
}
