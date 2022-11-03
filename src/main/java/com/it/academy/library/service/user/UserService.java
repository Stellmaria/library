package com.it.academy.library.service.user;

import com.it.academy.library.dto.create.user.UserCreateEditDto;
import com.it.academy.library.dto.filter.user.UserFilter;
import com.it.academy.library.dto.read.user.UserReadDto;
import com.it.academy.library.listener.entity.AccessType;
import com.it.academy.library.listener.entity.EntityEvent;
import com.it.academy.library.mapper.create.user.UserCreateEditMapper;
import com.it.academy.library.mapper.read.user.UserReadMapper;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.repository.entity.user.UserRepository;
import com.it.academy.library.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final ImageService imageService;

    public Page<UserReadDto> findAll(UserFilter userFilter, Pageable pageable) {
        var predicate = UserFilter.queryPredicates(userFilter);
        return userRepository.findAll(predicate, pageable)
                .map(it -> {
                    eventPublisher.publishEvent(new EntityEvent(it, AccessType.READ));
                    return userReadMapper.map(it);
                });
    }

    @SuppressWarnings("unused")
    public Collection<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(it -> {
                    eventPublisher.publishEvent(new EntityEvent(it, AccessType.READ));
                    return userReadMapper.map(it);
                })
                .collect(Collectors.toList());
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(it -> {
                    eventPublisher.publishEvent(new EntityEvent(it, AccessType.READ));
                    return userReadMapper.map(it);
                });
    }

    @Transactional(rollbackFor = {Exception.class})
    public UserReadDto create(UserCreateEditDto userCreateEditDto) {
        return Optional.of(userCreateEditDto)
                .map(it -> {
                    uploadImage(it.getImage());
                    return userCreateEditMapper.map(it);
                })
                .map(it -> {
                    eventPublisher.publishEvent(new EntityEvent(it, AccessType.CREATE));
                    return userRepository.save(it);
                })
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional(rollbackFor = {Exception.class})
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userCreateEditDto) {
        return userRepository.findById(id)
                .map(it -> {
                    uploadImage(userCreateEditDto.getImage());
                    return userCreateEditMapper.map(userCreateEditDto, it);
                })
                .map(it -> {
                    eventPublisher.publishEvent(new EntityEvent(it, AccessType.UPDATE));
                    return userRepository.saveAndFlush(it);
                })
                .map(userReadMapper::map);
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(it -> {
                    eventPublisher.publishEvent(new EntityEvent(it, AccessType.DELETE));
                    userRepository.delete(it);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @SneakyThrows
    private void uploadImage(@NotNull MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    public Optional<byte[]> findAvatar(Long id) {
        return userRepository.findById(id)
                .map(User::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::getImage);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getUserRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
