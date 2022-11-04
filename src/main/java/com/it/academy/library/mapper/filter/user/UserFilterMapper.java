package com.it.academy.library.mapper.filter.user;

import com.it.academy.library.dto.filter.user.UserFilter;
import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserFilterMapper implements Mapper<User, UserFilter> {
    @Override
    public UserFilter map(@NotNull User object) {
        return new UserFilter(
                object.getId(),
                object.getUsername(),
                object.getFirstName(),
                object.getLastName(),
                object.getEmail(),
                object.getPassword(),
                object.getUserRole() != null ? object.getUserRole().getId() : null,
                object.getUserStatus() != null ? object.getUserStatus().getId() : null,
                object.getBirthday()
        );
    }
}
