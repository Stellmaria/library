package com.it.academy.library.http.controller;

import com.it.academy.library.exception.NotEnoughProductsInStockException;
import com.it.academy.library.service.dto.PageResponse;
import com.it.academy.library.service.dto.create.OrderCreateEditDto;
import com.it.academy.library.service.dto.filter.order.OrderFilter;
import com.it.academy.library.service.entity.CartServiceImpl;
import com.it.academy.library.service.entity.book.BookService;
import com.it.academy.library.service.entity.order.OrderService;
import com.it.academy.library.service.entity.order.OrderStatusService;
import com.it.academy.library.service.entity.user.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderStatusService orderStatusService;
    private final UserService userService;
    private final BookService bookService;
    private final CartServiceImpl cartService;

    @GetMapping
    public String findAll(@NotNull Model model, OrderFilter filter, Pageable pageable) {
        model.addAttribute("orders", PageResponse.of(orderService.findAll(filter, pageable)));
        model.addAttribute("filter", filter);

        return "order/orders";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return orderService.findById(id)
                .map(dto -> {
                    model.addAttribute("order", dto);
                    model.addAttribute("statuses", orderStatusService.findAll());
                    model.addAttribute("users", userService.findAll());

                    return "order/order";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @Validated OrderCreateEditDto dto) {
        return orderService.update(id, dto)
                .map(it -> "redirect:/orders/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!orderService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/orders";
    }

    @GetMapping("/cart")
    public String cart(@NotNull Model model) {
        model.addAttribute("books", cartService.getBooks());

        return "order/cart";
    }

    @GetMapping("/cart/addBook/{id}")
    public String addBookToCart(@PathVariable("id") Long id) {
        return bookService.findById(id)
                .map(cartService::addBook)
                .map(it -> "redirect:/orders/cart")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cart/removeBook/{id}")
    public String removeBook(@PathVariable("id") Long id) {
        return bookService.findById(id)
                .map(cartService::removeBook)
                .map(it -> "redirect:/orders/cart")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("cart/create")
    public String create(RedirectAttributes redirectAttributes, @NotNull Principal principal) {
        try {
            cartService.checkout(userService.findByUsername(principal.getName()).orElse(null));
        } catch (NotEnoughProductsInStockException e) {
            redirectAttributes.addFlashAttribute("outOfStockMessage", e.getMessage());

            return "redirect:/orders/cart";
        }

        return "redirect:/books";
    }
}
