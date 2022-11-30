package com.it.academy.library.http.controller;

import com.it.academy.library.service.dto.PageResponse;
import com.it.academy.library.service.dto.create.book.BookPublishingHouseCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookPublishingHouseFilter;
import com.it.academy.library.service.entity.book.BookPublishingHouseService;
import com.it.academy.library.service.entity.book.BookService;
import com.it.academy.library.service.entity.user.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
@RequestMapping("/books/publishingHouses")
@RequiredArgsConstructor
public class BookPublishingHouseController {
    private final BookPublishingHouseService bookPublishingHouseService;
    private final BookService bookService;
    private final UserService userService;

    @PostMapping
    public String create(@Validated @NotNull BookPublishingHouseCreateEditDto dto,
                         @NotNull BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        validateName(dto, bindingResult);

        var view = checkError(
                dto, bindingResult, redirectAttributes, "redirect:/books/publishingHouses/addPublishingHouses"
        );

        if (view != null) {
            return view;
        }

        bookPublishingHouseService.create(dto);

        return "redirect:/books/publishingHouses";
    }

    @GetMapping
    public String findAll(@NotNull Model model, BookPublishingHouseFilter filter, Pageable pageable) {
        model.addAttribute(
                "publishingHouses", PageResponse.of(bookPublishingHouseService.findAll(filter, pageable))
        );
        model.addAttribute("filter", filter);
        model.addAttribute("users", userService.findAll());

        return "book/publishingHouses/publishingHouses";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return bookPublishingHouseService.findById(id)
                .map(dto -> {
                    model.addAttribute("publishingHouse", dto);
                    model.addAttribute("books", bookService.findAllByBookPublishingHouseId(id));
                    model.addAttribute("users", userService.findAll());

                    return "book/publishingHouses/publishingHouse";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id,
                         @Validated @NotNull BookPublishingHouseCreateEditDto dto,
                         @NotNull BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        validateName(dto, bindingResult);

        var view = checkError(
                dto, bindingResult, redirectAttributes, "redirect:/books/publishingHouses/{id}"
        );

        return view != null
                ? view
                : bookPublishingHouseService.update(id, dto)
                        .map(it -> "redirect:/books/publishingHouses/{id}")
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!bookPublishingHouseService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/books/publishingHouses";
    }

    @GetMapping("/addPublishingHouses")
    public String addBook(@NotNull Model model, BookPublishingHouseCreateEditDto dto) {
        model.addAttribute("publishingHouses", dto);
        model.addAttribute("users", userService.findAll());

        return "book/publishingHouses/addPublishingHouses";
    }

    private void validateName(@NotNull BookPublishingHouseCreateEditDto dto, @NotNull BindingResult bindingResult) {
        if (bookPublishingHouseService.findByName(dto.getName()).isPresent()) {
            bindingResult.rejectValue(
                    "name", "error.bookPublishingHouse",
                    "A publishing house with the same name already exists."
            );
        }
    }

    private @Nullable String checkError(@NotNull BookPublishingHouseCreateEditDto dto,
                                        @NotNull BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes,
                                        String view) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("publishingHouse", dto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return view;
        }
        return null;
    }
}
