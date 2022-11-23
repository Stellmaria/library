package com.it.academy.library.http.controller;

import com.it.academy.library.exception.NotEnoughProductsInStockException;
import com.it.academy.library.mapper.convert.book.BookMapper;
import com.it.academy.library.service.ShoppingCartService;
import com.it.academy.library.service.entity.book.BookService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shoppingCart")
public class CartController {
    private final ShoppingCartService shoppingCartService;
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public String shoppingCart(@NotNull Model model) {
        model.addAttribute("books", shoppingCartService.getBooks());

        return "/shoppingCart";
    }

    @GetMapping("/addProduct/{productId}")
    public String addProductToCart(@PathVariable("productId") Long productId) {
        return bookService.findById(productId)
                .map(bookMapper::map)
                .map(shoppingCartService::addProduct)
                .map(it -> "redirect:/shoppingCart")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/removeProduct/{productId}")
    public String removeProductFromCart(@PathVariable("productId") Long productId) {
        return bookService.findById(productId)
                .map(bookMapper::map)
                .map(shoppingCartService::removeProduct)
                .map(it -> "redirect:/shoppingCart")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/checkout")
    public String checkout(RedirectAttributes redirectAttributes) {
        try {
            shoppingCartService.checkout();
        } catch (NotEnoughProductsInStockException e) {
            redirectAttributes.addFlashAttribute("outOfStockMessage", e.getMessage());

            return "redirect:/shoppingCart";
        }

        return "redirect:/books";
    }
}
