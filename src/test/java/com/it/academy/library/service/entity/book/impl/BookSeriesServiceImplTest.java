package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.model.repository.entity.book.BookSeriesRepository;
import com.it.academy.library.service.dto.create.book.BookSeriesCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookSeriesFilter;
import com.it.academy.library.service.entity.book.BookSeriesService;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
@DisplayName("Book series service test.")
class BookSeriesServiceImplTest extends IntegrationTestBase {
    private final BookSeriesService bookSeriesService;

    private final BookSeriesRepository bookSeriesRepository;

    @Test
    @DisplayName("Save book series.")
    void create() {
        var bookSeries = new BookSeriesCreateEditDto(ConstantUtil.SAVE + ConstantUtil.NEW);

        var actual = bookSeriesService.create(bookSeries);

        assertEquals(actual.getName(), bookSeries.getName(), "The names must match.");
    }

    @Test
    @DisplayName("Find book series by id.")
    void findById() {
        var expected = ConstantUtil.getBookSeriesDarkTown();

        var actual = bookSeriesService.findById(expected.getId());

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(expected.getName(), entity.getName(), "The names must match."),
                        () -> assertEquals(expected.getId(), entity.getId(), "The ids must match.")
                )
        );

    }

    @Test
    @DisplayName("Find all book series.")
    void findAll() {
        var expected = bookSeriesRepository.count();

        var actual = bookSeriesService.findAll();

        assertThat(actual).hasSize(Math.toIntExact(expected));
    }

    @Test
    @DisplayName("Find all book series with filter.")
    void findAllWithFilter() {
        var expected = 1;
        var filter = new BookSeriesFilter();
        filter.setName(ConstantUtil.getBookSeriesDarkTown().getName());

        var actual = bookSeriesService.findAll(filter, Pageable.ofSize(ConstantUtil.PAGE_SIZE));

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find book series by name.")
    void findByName() {
        var expected = ConstantUtil.getBookSeriesDarkTown();

        var actual = bookSeriesService.findByName(expected.getName());

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(expected.getName(), entity.getName(), "The names must match."),
                        () -> assertEquals(expected.getId(), entity.getId(), "The ids must match.")
                )
        );
    }

    @Test
    @DisplayName("Update book series.")
    void update() {
        var bookSeries = new BookSeriesCreateEditDto(ConstantUtil.UPDATE + ConstantUtil.NEW);

        var actual = bookSeriesService.update(ConstantUtil.BOOK_SERIES_ID_4, bookSeries);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(bookSeries.getName(), entity.getName(), "The names must match."),
                        () -> assertEquals(ConstantUtil.BOOK_SERIES_ID_4, entity.getId(), "The ids must match.")
                )
        );
    }

    @Test
    @DisplayName("Delete book series")
    void delete() {
        assertAll(
                () -> assertTrue(bookSeriesService.delete(ConstantUtil.BOOK_SERIES_ID_5)),
                () -> assertFalse(bookSeriesService.delete(ConstantUtil.BOOK_SERIES_ID_99))
        );
    }
}
