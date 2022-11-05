package com.it.academy.library.mapper.filter.user;

import com.it.academy.library.dto.filter.user.UserFilter;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.User;
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
        var userRole = Optional.ofNullable(object.getUserRole())
                .map(userRoleFilterMapper::map)
                .orElse(null);
        var userStatus = Optional.ofNullable(object.getUserStatus())
                .map(userStatusFilterMapper::map)
                .orElse(null);

        return new UserFilter(
                object.getId(),
                object.getUsername(),
                object.getFirstName(),
                object.getLastName(),
                object.getEmail(),
                object.getPassword(),
                userRole,
                userStatus,
                object.getBirthday()
        );
    }
}
