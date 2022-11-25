package com.it.academy.library.http.controller;

import com.it.academy.library.service.dto.PageResponse;
import com.it.academy.library.service.dto.create.book.BookSeriesCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookSeriesFilter;
import com.it.academy.library.service.entity.book.BookSeriesService;
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
@RequestMapping("/books/series")
@RequiredArgsConstructor
public class BookSeriesController {
    private final BookSeriesService bookSeriesService;
    private final BookService bookService;

    @PostMapping
    public String create(@Validated @NotNull BookSeriesCreateEditDto dto, @NotNull BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bookSeriesService.findByName(dto.getName()).isPresent()) {
            bindingResult.rejectValue(
                    "name", "error.bookSeries",
                    "A book series with the same name already exists."
            );
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("series", dto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/books/series/addSeries";
        }

        bookSeriesService.create(dto);

        return "redirect:/books/series";
    }

    @GetMapping
    public String findAll(@NotNull Model model, BookSeriesFilter filter, Pageable pageable) {
        model.addAttribute("series", PageResponse.of(bookSeriesService.findAll(filter, pageable)));
        model.addAttribute("filter", filter);

        return "book/series/series";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return bookSeriesService.findById(id)
                .map(dto -> {
                    model.addAttribute("series", dto);
                    model.addAttribute("books", bookService.findAllBySeriesId(id));

                    return "book/series/bookSeries";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, @Validated BookSeriesCreateEditDto dto) {
        return bookSeriesService.update(id, dto)
                .map(it -> "redirect:/books/series/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!bookSeriesService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/books/series";
    }

    @GetMapping("/addSeries")
    public String addBook(@NotNull Model model, BookSeriesCreateEditDto dto) {
        model.addAttribute("series", dto);

        return "book/series/addSeries";
    }
}
