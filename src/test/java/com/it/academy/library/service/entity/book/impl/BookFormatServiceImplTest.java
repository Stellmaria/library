package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.model.repository.entity.book.BookFormatRepository;
import com.it.academy.library.service.entity.book.BookFormatService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@DisplayName("Book service test.")
class BookFormatServiceImplTest extends IntegrationTestBase {
    private final BookFormatRepository bookFormatRepository;

    private final BookFormatService bookFormatService;

    @Test
    @DisplayName("Find all book format.")
    void findAll() {
        var expected = bookFormatRepository.count();

        var actual = bookFormatService.findAll();

        assertThat(actual).hasSize(Math.toIntExact(expected));
    }
}