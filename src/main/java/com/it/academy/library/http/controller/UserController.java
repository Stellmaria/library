package com.it.academy.library.http.controller;

import com.it.academy.library.service.dto.PageResponse;
import com.it.academy.library.service.dto.create.user.UserCreateEditDto;
import com.it.academy.library.service.dto.filter.user.UserFilter;
import com.it.academy.library.service.entity.user.UserRoleService;
import com.it.academy.library.service.entity.user.UserService;
import com.it.academy.library.service.entity.user.UserStatusService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final UserStatusService userStatusService;

    @PostMapping
    public String create(@Validated UserCreateEditDto dto,
                         @NotNull BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", dto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/users/registration";
        }

        userService.create(dto);

        return "redirect:/";
    }

    @GetMapping
    public String findAll(@NotNull Model model, UserFilter filter, Pageable pageable) {
        var page = userService.findAll(filter, pageable);

        model.addAttribute("users", PageResponse.of(page));
        model.addAttribute("filter", filter);

        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return userService.findById(id)
                .map(dto -> {
                    model.addAttribute("user", dto);
                    model.addAttribute("roles", userRoleService.findAll());
                    model.addAttribute("statuses", userStatusService.findAll());

                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @Validated UserCreateEditDto dto) {
        return userService.update(id, dto)
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

    @GetMapping("/registration")
    public String registration(@NotNull Model model, UserCreateEditDto user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", userRoleService.findAll());
        model.addAttribute("statuses", userStatusService.findAll());

        return "user/registration";
    }
}
