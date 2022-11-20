package com.it.academy.library.http.controller.rest;

import com.it.academy.library.service.dto.PageResponse;
import com.it.academy.library.service.dto.create.book.BookCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import com.it.academy.library.service.dto.read.book.BookReadDto;
import com.it.academy.library.service.entity.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookReadDto create(@Validated @RequestBody BookCreateEditDto dto) {
        return bookService.create(dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<BookReadDto> findAll(BookFilter filter, Pageable pageable) {
        return PageResponse.of(bookService.findAll(filter, pageable));
    }

    @GetMapping("/{id}")
    public BookReadDto findById(@PathVariable("id") Long id) {
        return bookService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public BookReadDto update(@PathVariable("id") Long id, @Validated @RequestBody BookCreateEditDto dto) {
        return bookService.update(id, dto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        if (!bookService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/image")
    public ResponseEntity<byte[]> findAvatar(@PathVariable("id") Long id) {
        return bookService.findImage(id)
                .map(it -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .contentLength(it.length)
                        .body(it))
                .orElseGet(notFound()::build);
    }
}
