package com.it.academy.library.http.controller.rest;

import com.it.academy.library.service.dto.PageResponse;
import com.it.academy.library.service.dto.create.user.UserCreateEditDto;
import com.it.academy.library.service.dto.filter.user.UserFilter;
import com.it.academy.library.service.dto.read.user.UserReadDto;
import com.it.academy.library.service.entity.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<UserReadDto> findAll(UserFilter userFilter, Pageable pageable) {
        var page = userService.findAll(userFilter, pageable);

        return PageResponse.of(page);
    }

    @GetMapping(value = "/{id}/avatar")
    public ResponseEntity<byte[]> findAvatar(@PathVariable("id") Long id) {
        return userService.findAvatar(id)
                .map(it -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .contentLength(it.length)
                        .body(it))
                .orElseGet(notFound()::build);
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@Validated @RequestBody UserCreateEditDto user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable("id") Long id, @Validated @RequestBody UserCreateEditDto user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
