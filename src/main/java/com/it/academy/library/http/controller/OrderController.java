package com.it.academy.library.http.controller;

import com.it.academy.library.service.dto.PageResponse;
import com.it.academy.library.service.dto.filter.order.OrderFilter;
import com.it.academy.library.service.entity.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public String findAll(@NotNull Model model, OrderFilter filter, Pageable pageable) {
        model.addAttribute("orders", PageResponse.of(orderService.findAll(filter, pageable)));
        model.addAttribute("filter", filter);

        return "order/orders";
    }
}
