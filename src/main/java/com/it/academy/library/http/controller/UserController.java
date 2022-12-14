package com.it.academy.library.http.controller;

import com.it.academy.library.service.dto.PageResponse;
import com.it.academy.library.service.dto.create.UserCreateEditDto;
import com.it.academy.library.service.dto.filter.user.UserFilter;
import com.it.academy.library.service.entity.user.UserRoleService;
import com.it.academy.library.service.entity.user.UserService;
import com.it.academy.library.service.entity.user.UserStatusService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.http.ResponseEntity.notFound;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final UserStatusService userStatusService;

    @PostMapping
    public String create(@Validated @NotNull UserCreateEditDto dto,
                         @NotNull BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        validate(dto, bindingResult);

        var view = checkError(dto, bindingResult, redirectAttributes, "redirect:/registration");
        if (view != null) {
            return view;
        }

        userService.create(dto);

        return "redirect:/login";
    }

    @GetMapping
    public String findAll(@NotNull Model model, UserFilter filter, Pageable pageable) {
        model.addAttribute("users", PageResponse.of(userService.findAll(filter, pageable)));
        model.addAttribute("filter", filter);
        model.addAttribute("allUsers", userService.findAll());

        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return userService.findById(id)
                .map(dto -> {
                    model.addAttribute("user", dto);
                    model.addAttribute("roles", userRoleService.findAll());
                    model.addAttribute("statuses", userStatusService.findAll());
                    model.addAttribute("users", userService.findAll());

                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @Validated @NotNull UserCreateEditDto dto,
                         @NotNull BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        var view = checkError(dto, bindingResult, redirectAttributes, "redirect:/users/{id}");

        return view != null
                ? view
                : userService.update(id, dto, SecurityContextHolder.getContext().getAuthentication())
                        .map(it -> "redirect:/users/{id}")
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/users";
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

    private void validate(@NotNull UserCreateEditDto dto, @NotNull BindingResult bindingResult) {
        if (userService.findByEmail(dto.getEmail()).isPresent()) {
            bindingResult.rejectValue(
                    "email", "error.user",
                    "There is already a user registered with the email provided"
            );
        }

        if (userService.findByUsername(dto.getUsername()).isPresent()) {
            bindingResult.rejectValue(
                    "username", "error.user",
                    "There is already a user registered with the username provided"
            );
        }
    }

    private @Nullable String checkError(@NotNull UserCreateEditDto dto,
                                        @NotNull BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes,
                                        String view) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", dto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return view;
        }
        return null;
    }
}
