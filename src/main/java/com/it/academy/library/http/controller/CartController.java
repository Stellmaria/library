package com.it.academy.library.http.controller;

import com.it.academy.library.exception.NotEnoughProductsInStockException;
import com.it.academy.library.mapper.convert.book.BookMapper;
import com.it.academy.library.service.ShoppingCartService;
import com.it.academy.library.service.entity.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shoppingCart")
public class CartController {
    private final ShoppingCartService shoppingCartService;
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");

        modelAndView.addObject("products", shoppingCartService.getBooks());

        return modelAndView;
    }

    @GetMapping("/addProduct/{productId}")
    public ModelAndView addProductToCart(@PathVariable("productId") Long productId) {
        bookService.findById(productId)
                .map(bookMapper::map)
                .ifPresent(shoppingCartService::addProduct);

        return shoppingCart();
    }

    @GetMapping("/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
        bookService.findById(productId)
                .map(bookMapper::map)
                .ifPresent(shoppingCartService::removeProduct);

        return shoppingCart();
    }

    @GetMapping("/checkout")
    public ModelAndView checkout() {
        try {
            shoppingCartService.checkout();
        } catch (NotEnoughProductsInStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        return shoppingCart();
    }
}
