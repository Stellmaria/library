package com.it.academy.library.service.entity.user;

import com.it.academy.library.service.dto.create.UserCreateEditDto;
import com.it.academy.library.service.dto.filter.user.UserFilter;
import com.it.academy.library.service.dto.read.user.UserReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    /**
     * Creating a new user.
     *
     * @param dto for creating.
     * @return new created user.
     */
    UserReadDto create(UserCreateEditDto dto);

    /**
     * Search for a user by user id.
     *
     * @param id for search.
     * @return user.
     */
    Optional<UserReadDto> findById(Long id);

    /**
     * Search for all users with filtering.
     *
     * @param filter   for search.
     * @param pageable for search.
     * @return collection of found users.
     */
    Page<UserReadDto> findAll(UserFilter filter, Pageable pageable);

    /**
     * Search for all users.
     *
     * @return collection of found users.
     */
    Collection<UserReadDto> findAll();

    /**
     * Search for a user by user username.
     *
     * @param username for search.
     * @return user.
     */
    Optional<UserReadDto> findByUsername(String username);

    /**
     * Search for a user by user username.
     *
     * @param email for search.
     * @return user.
     */
    Optional<UserReadDto> findByEmail(String email);

    /**
     * Update user by user id.
     *
     * @param id  to update.
     * @param dto for an update.
     * @return updated user.
     */
    Optional<UserReadDto> update(Long id, UserCreateEditDto dto);

    /**
     * Deleting a user by user id.
     *
     * @param id for removing.
     * @return true - succeeded; false - failed.
     */
    boolean delete(Long id);

    /**
     * Search for a user avatar by user id.
     *
     * @param id for search.
     * @return user avatar.
     */
    Optional<byte[]> findAvatar(Long id);
}
