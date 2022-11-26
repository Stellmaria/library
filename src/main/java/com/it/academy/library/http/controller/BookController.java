package com.it.academy.library.http.controller;

import com.it.academy.library.service.dto.PageResponse;
import com.it.academy.library.service.dto.create.book.BookCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import com.it.academy.library.service.entity.author.AuthorService;
import com.it.academy.library.service.entity.book.BookFormatService;
import com.it.academy.library.service.entity.book.BookGenreService;
import com.it.academy.library.service.entity.book.BookLanguageService;
import com.it.academy.library.service.entity.book.BookPublishingHouseService;
import com.it.academy.library.service.entity.book.BookSeriesService;
import com.it.academy.library.service.entity.book.BookService;
import com.it.academy.library.service.entity.book.BookStatusService;
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
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookStatusService bookStatusService;
    private final BookLanguageService bookLanguageService;
    private final BookFormatService bookFormatService;
    private final BookPublishingHouseService bookPublishingHouseService;
    private final BookSeriesService bookSeriesService;
    private final AuthorService authorService;
    private final BookGenreService bookGenreService;

    @PostMapping
    public String create(@Validated @NotNull BookCreateEditDto dto, @NotNull BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        var view = checkError(dto, bindingResult, redirectAttributes, "redirect:/books/addBook");
        if (view != null) {
            return view;
        }

        bookService.create(dto);

        return "redirect:/books";
    }

    @GetMapping
    public String findAll(@NotNull Model model, BookFilter filter, Pageable pageable) {
        model.addAttribute("books", PageResponse.of(bookService.findAll(filter, pageable)));
        model.addAttribute("filter", filter);

        return "book/books";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return bookService.findById(id)
                .map(book -> {
                    model.addAttribute("book", book);
                    model.addAttribute("statuses", bookStatusService.findAll());
                    model.addAttribute("languages", bookLanguageService.findAll());
                    model.addAttribute("formats", bookFormatService.findAll());
                    model.addAttribute("houses", bookPublishingHouseService.findAll());
                    model.addAttribute("series", bookSeriesService.findAll());
                    model.addAttribute("authors", authorService.findAllByBookId(id));
                    model.addAttribute("allAuthors", authorService.findAll());
                    model.addAttribute("allGenres", bookGenreService.findAll());
                    model.addAttribute("genres", bookGenreService.findAllByBookId(id));

                    return "book/book";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @Validated BookCreateEditDto dto,
                         @NotNull BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        var view = checkError(dto, bindingResult, redirectAttributes, "redirect:/books/{id}");
        if (view != null) {
            return view;
        }

        return bookService.update(id, dto)
                .map(it -> "redirect:/books/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!bookService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/books";
    }

    @GetMapping("/addBook")
    public String addBook(@NotNull Model model, BookCreateEditDto dto) {
        model.addAttribute("book", dto);
        model.addAttribute("statuses", bookStatusService.findAll());
        model.addAttribute("languages", bookLanguageService.findAll());
        model.addAttribute("formats", bookFormatService.findAll());
        model.addAttribute("houses", bookPublishingHouseService.findAll());
        model.addAttribute("series", bookSeriesService.findAll());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", bookGenreService.findAll());

        return "book/addBook";
    }

    private @Nullable String checkError(@NotNull BookCreateEditDto dto, @NotNull BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes, String page) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("book", dto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return page;
        }
        return null;
    }
}
