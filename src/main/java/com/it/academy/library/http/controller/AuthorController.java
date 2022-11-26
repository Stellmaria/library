package com.it.academy.library.http.controller;

import com.it.academy.library.service.dto.PageResponse;
import com.it.academy.library.service.dto.create.AuthorCreateEditDto;
import com.it.academy.library.service.dto.filter.AuthorFilter;
import com.it.academy.library.service.entity.author.AuthorService;
import com.it.academy.library.service.entity.book.BookService;
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
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    @PostMapping
    public String create(@Validated @NotNull AuthorCreateEditDto dto, @NotNull BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        var view = checkError(dto, bindingResult, redirectAttributes, "redirect:/authors/addAuthor");
        if (view != null) {
            return view;
        }

        authorService.create(dto);

        return "redirect:/authors";
    }


    @GetMapping
    public String findAll(@NotNull Model model, AuthorFilter filter, Pageable pageable) {
        model.addAttribute("authors", PageResponse.of(authorService.findAll(filter, pageable)));
        model.addAttribute("filter", filter);

        return "author/authors";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return authorService.findById(id)
                .map(author -> {
                    model.addAttribute("author", author);
                    model.addAttribute("books", bookService.findAllByAuthorId(id));

                    return "author/author";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @Validated AuthorCreateEditDto dto,
                         @NotNull BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        var view = checkError(dto, bindingResult, redirectAttributes, "redirect:/authors/{id}");
        if (view != null) {
            return view;
        }

        return authorService.update(id, dto)
                .map(it -> "redirect:/authors/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!authorService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/authors";
    }

    @GetMapping("/addAuthor")
    public String addAuthor(@NotNull Model model, AuthorCreateEditDto dto) {
        model.addAttribute("author", dto);

        return "author/addAuthor";
    }

    private @Nullable String checkError(@NotNull AuthorCreateEditDto dto, @NotNull BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes, String page) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("author", dto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return page;
        }
        return null;
    }
}
