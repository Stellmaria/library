package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.model.repository.entity.book.BookStatusRepository;
import com.it.academy.library.service.entity.book.BookStatusService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@DisplayName("Book status service test.")
class BookStatusServiceImplTest extends IntegrationTestBase {
    private final BookStatusRepository bookStatusRepository;

    private final BookStatusService bookStatusService;

    @Test
    @DisplayName("Find all book status.")
    void findAll() {
        var expected = bookStatusRepository.count();

        var actual = bookStatusService.findAll();

        assertThat(actual).hasSize(Math.toIntExact(expected));
    }
}
