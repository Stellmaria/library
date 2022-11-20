package com.it.academy.library.mapper.create;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.model.repository.entity.user.UserRoleRepository;
import com.it.academy.library.model.repository.entity.user.UserStatusRepository;
import com.it.academy.library.service.dto.create.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {
    private final UserRoleRepository userRoleRepository;
    private final UserStatusRepository userStatusRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(@NotNull UserCreateEditDto object) {
        var user = new User();

        copy(object, user);

        return user;
    }

    @Override
    public User map(@NotNull UserCreateEditDto fromObject, @NotNull User toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    private void copy(@NotNull UserCreateEditDto object, @NotNull User user) {
        user.setUsername(object.getUsername());
        user.setFirstName(object.getFirstName());
        user.setLastName(object.getLastName());
        user.setEmail(object.getEmail());
        user.setUserRole(getUserRole(object.getUserRoleId()));
        user.setUserStatus(getUserStatus(object.getUserStatusId()));
        user.setBirthday(object.getBirthday());
        setPassword(object, user);
        setImage(object, user);
    }

    private void setImage(@NotNull UserCreateEditDto object, @NotNull User user) {
        Optional.ofNullable(object.getImage())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(image -> user.setImage(image.getOriginalFilename()));
    }

    private void setPassword(@NotNull UserCreateEditDto object, @NotNull User user) {
        Optional.ofNullable(object.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);
    }

    private UserRole getUserRole(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(userRoleRepository::findById)
                .orElse(null);
    }

    private UserStatus getUserStatus(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(userStatusRepository::findById)
                .orElse(null);
    }
}
