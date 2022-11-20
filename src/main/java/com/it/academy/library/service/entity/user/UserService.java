package com.it.academy.library.service.entity.user;

import com.it.academy.library.mapper.create.UserCreateEditMapper;
import com.it.academy.library.mapper.read.user.UserReadMapper;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.repository.entity.user.UserRepository;
import com.it.academy.library.service.ImageService;
import com.it.academy.library.service.dto.create.UserCreateEditDto;
import com.it.academy.library.service.dto.filter.user.UserFilter;
import com.it.academy.library.service.dto.read.user.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }

    @Transactional(rollbackFor = {Exception.class})
    public UserReadDto create(UserCreateEditDto dto) {
        return Optional.of(dto)
                .map(it -> {
                    Optional.ofNullable(it.getImage())
                            .ifPresent(multipartFile -> ImageService.uploadImage(multipartFile, imageService));

                    it.setUserRoleId(1);
                    it.setUserStatusId(1);

                    return userCreateEditMapper.map(it);
                })
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        var predicate = UserFilter.queryPredicates(filter);

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::map);
    }

    @SuppressWarnings("unused")
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
    public Optional<UserReadDto> update(Long id, UserCreateEditDto dto) {
        return userRepository.findById(id)
                .map(it -> {
                    ImageService.uploadImage(dto.getImage(), imageService);

                    return userCreateEditMapper.map(dto, it);
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

    public Optional<byte[]> findAvatar(Long id) {
        return userRepository.findById(id)
                .map(User::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::getImage);
    }
}
