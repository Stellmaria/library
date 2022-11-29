package com.it.academy.library.service.entity.user.impl;

import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.create.UserCreateEditMapper;
import com.it.academy.library.mapper.read.user.UserReadMapper;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.model.repository.entity.user.UserRepository;
import com.it.academy.library.service.dto.create.UserCreateEditDto;
import com.it.academy.library.service.dto.filter.user.UserFilter;
import com.it.academy.library.service.dto.read.user.UserReadDto;
import com.it.academy.library.service.entity.user.UserService;
import com.it.academy.library.service.image.ImageService;
import com.it.academy.library.service.image.impl.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    private final ImageService imageService;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var filter = new UserFilter();
        filter.setUsername(username);

        return userRepository.findAllByUserFilter(filter).stream()
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getUserRole())
                ))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public UserReadDto create(UserCreateEditDto dto) {
        return Optional.of(dto)
                .map(entity -> {

                    entity.setUserRoleId(1);
                    entity.setUserStatusId(1);

                    return userCreateEditMapper.map(entity);
                })
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.CREATE));

                    entity.setImage("avatar_1.jpg");

                    return userRepository.save(entity);
                })
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return userReadMapper.map(entity);
                });
    }

    @Override
    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        return userRepository.findAll(UserFilter.queryPredicates(filter), pageable)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return userReadMapper.map(entity);
                });
    }

    @Override
    public Collection<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return userReadMapper.map(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserReadDto> findByUsername(String username) {
        var filter = new UserFilter();
        filter.setUsername(username);

        return userRepository.findAllByUserFilter(filter).stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return userReadMapper.map(entity);
                })
                .findFirst();
    }

    @Override
    public Optional<UserReadDto> findByEmail(String email) {
        var filter = new UserFilter();
        filter.setEmail(email);

        return userRepository.findAllByUserFilter(filter).stream()
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

                    return userReadMapper.map(entity);
                })
                .findFirst();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Optional<UserReadDto> update(Long id, UserCreateEditDto dto, Authentication authentication) {
        return userRepository.findById(id)
                .map(entity -> {
                    ImageServiceImpl.uploadImage(dto.getImage(), imageService);

                    return userCreateEditMapper.map(dto, entity);
                })
                .map(entity -> {
                    var admin = authentication.getAuthorities().stream()
                            .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
                    if (!admin) {
                        var userStatus = new UserStatus();
                        userStatus.setId(3);
                        var userRole = new UserRole();
                        userRole.setId(3);

                        entity.setUserRole(userRole);
                        entity.setUserStatus(userStatus);
                    }

                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.UPDATE));

                    return userRepository.saveAndFlush(entity);
                })
                .map(userReadMapper::map);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.DELETE));

                    userRepository.delete(entity);
                    userRepository.flush();

                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<byte[]> findAvatar(Long id) {
        return userRepository.findById(id)
                .map(User::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::getImage);
    }
}
