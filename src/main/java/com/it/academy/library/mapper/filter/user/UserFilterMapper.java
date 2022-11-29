package com.it.academy.library.mapper.filter.user;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.service.dto.filter.user.UserFilter;
import com.it.academy.library.service.dto.filter.user.UserRoleFilter;
import com.it.academy.library.service.dto.filter.user.UserStatusFilter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserFilterMapper implements Mapper<User, UserFilter> {
    private final UserRoleFilterMapper userRoleFilterMapper;
    private final UserStatusFilterMapper userStatusFilterMapper;

    @Override
    public UserFilter map(@NotNull User object) {
        return new UserFilter(
                object.getId(),
                object.getUsername(),
                object.getFirstName(),
                object.getLastName(),
                object.getEmail(),
                object.getPassword(),
                getUserRole(object),
                getUserStatus(object),
                object.getBirthday()
        );
    }

    private UserStatusFilter getUserStatus(@NotNull User object) {
        return Optional.ofNullable(object.getUserStatus())
                .map(userStatusFilterMapper::map)
                .orElse(null);
    }

    private UserRoleFilter getUserRole(@NotNull User object) {
        return Optional.ofNullable(object.getUserRole())
                .map(userRoleFilterMapper::map)
                .orElse(null);
    }
}
