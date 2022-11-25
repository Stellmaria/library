package com.it.academy.library.http.controller;

import com.it.academy.library.service.dto.create.UserCreateEditDto;
import com.it.academy.library.service.entity.user.UserRoleService;
import com.it.academy.library.service.entity.user.UserStatusService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserRoleService userRoleService;
    private final UserStatusService userStatusService;

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/registration")
    public String registration(@NotNull Model model, UserCreateEditDto user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", userRoleService.findAll());
        model.addAttribute("statuses", userStatusService.findAll());

        return "user/registration";
    }
}
