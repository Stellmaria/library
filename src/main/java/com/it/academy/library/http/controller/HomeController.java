package com.it.academy.library.http.controller;

import com.it.academy.library.service.entity.user.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping("/")
    public String homePage(@NotNull Model model) {
        model.addAttribute("users", userService.findAll());

        return "main";
    }
}
