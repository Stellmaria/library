package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.model.repository.entity.book.BookLanguageRepository;
import com.it.academy.library.service.entity.book.BookLanguageService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@DisplayName("Book language service test.")
class BookLanguageServiceImplTest extends IntegrationTestBase {
    private final BookLanguageRepository bookLanguageRepository;

    private final BookLanguageService bookLanguageService;

    @Test
    @DisplayName("Find all book language.")
    void findAll() {
        var expected = bookLanguageRepository.count();

        var actual = bookLanguageService.findAll();

        assertThat(actual).hasSize(Math.toIntExact(expected));
    }
}
