package com.it.academy.library.http.controller;

import com.it.academy.library.service.dto.PageResponse;
import com.it.academy.library.service.dto.create.book.BookGenreCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookGenreFilter;
import com.it.academy.library.service.entity.book.BookGenreService;
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
@RequestMapping("/books/genres")
@RequiredArgsConstructor
public class BookGenreController {
    private final BookGenreService bookGenreService;
    private final BookService bookService;

    @PostMapping
    public String create(@Validated @NotNull BookGenreCreateEditDto dto, @NotNull BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bookGenreService.findByName(dto.getName()).isPresent()) {
            bindingResult.rejectValue(
                    "name", "error.bookGenre", "There is already a book genre with that name."
            );
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("genre", dto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/books/genres/addGenre";
        }

        bookGenreService.create(dto);

        return "redirect:/books/genres";
    }

    @GetMapping
    public String findAll(@NotNull Model model, BookGenreFilter filter, Pageable pageable) {
        model.addAttribute("genres", PageResponse.of(bookGenreService.findAll(filter, pageable)));
        model.addAttribute("filter", filter);

        return "book/genre/bookGenres";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return bookGenreService.findById(id)
                .map(dto -> {
                    model.addAttribute("genre", dto);
                    model.addAttribute("books", bookService.findAllByBookGenreId(id));

                    return "book/genre/bookGenre";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, @Validated BookGenreCreateEditDto dto) {
        return bookGenreService.update(id, dto)
                .map(it -> "redirect:/books/genres/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!bookGenreService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/books/genres";
    }

    @GetMapping("/addGenre")
    public String addBook(@NotNull Model model, BookGenreCreateEditDto dto) {
        model.addAttribute("genre", dto);

        return "book/genre/addBookGenre";
    }
}
