package com.it.academy.library.http.controller;

import com.it.academy.library.service.dto.PageResponse;
import com.it.academy.library.service.dto.create.book.BookPublishingHouseCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookPublishingHouseFilter;
import com.it.academy.library.service.entity.book.BookPublishingHouseService;
import com.it.academy.library.service.entity.book.BookService;
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
@RequestMapping("/books/publishingHouses")
@RequiredArgsConstructor
public class BookPublishingHouseController {
    private final BookPublishingHouseService bookPublishingHouseService;
    private final BookService bookService;

    @PostMapping
    public String create(@Validated @NotNull BookPublishingHouseCreateEditDto dto, @NotNull BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bookPublishingHouseService.findByName(dto.getName()).isPresent()) {
            bindingResult.rejectValue(
                    "name", "error.bookPublishingHouse",
                    "A publishing house with the same name already exists."
            );
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("publishingHouse", dto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/books/publishingHouses/addPublishingHouses";
        }

        bookPublishingHouseService.create(dto);

        return "redirect:/authors";

    }

    @GetMapping
    public String findAll(@NotNull Model model, BookPublishingHouseFilter filter, Pageable pageable) {
        model.addAttribute(
                "publishingHouses", PageResponse.of(bookPublishingHouseService.findAll(filter, pageable))
        );
        model.addAttribute("filter", filter);

        return "book/publishingHouses/publishingHouses";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return bookPublishingHouseService.findById(id)
                .map(dto -> {
                    model.addAttribute("publishingHouse", dto);
                    model.addAttribute("books", bookService.findAllByBookPublishingHouseId(id));

                    return "book/publishingHouses/publishingHouse";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, @Validated BookPublishingHouseCreateEditDto dto) {
        return bookPublishingHouseService.update(id, dto)
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

        return "book/publishingHouses/addPublishingHouses";
    }
}
