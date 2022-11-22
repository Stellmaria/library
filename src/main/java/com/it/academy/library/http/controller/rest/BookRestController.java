package com.it.academy.library.http.controller.rest;

import com.it.academy.library.service.entity.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

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
