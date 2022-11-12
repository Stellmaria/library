package com.it.academy.library.service.entity.user;

import com.it.academy.library.mapper.create.user.UserCreateEditMapper;
import com.it.academy.library.mapper.read.user.UserReadMapper;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.repository.entity.user.UserRepository;
import com.it.academy.library.service.ImageService;
import com.it.academy.library.service.dto.create.user.UserCreateEditDto;
import com.it.academy.library.service.dto.filter.user.UserFilter;
import com.it.academy.library.service.dto.read.user.UserReadDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
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
    private final ImageService imageService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getUserRole())
                )).orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }

    public Page<UserReadDto> findAll(UserFilter userFilter, Pageable pageable) {
        var predicate = UserFilter.queryPredicates(userFilter);

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::map);
    }

    public Collection<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional(rollbackFor = {Exception.class})
    public UserReadDto create(UserCreateEditDto userCreateEditDto) {
        return Optional.of(userCreateEditDto)
                .map(it -> {
                    Optional.ofNullable(it.getImage()).ifPresent(this::uploadImage);

                    it.setUserRoleId(1);
                    it.setUserStatusId(1);

                    return userCreateEditMapper.map(it);
                })
                .map(userRepository::save)
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
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(it -> {
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
}