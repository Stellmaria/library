package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.model.repository.entity.book.BookPublishingHouseRepository;
import com.it.academy.library.service.dto.create.book.BookPublishingHouseCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookPublishingHouseFilter;
import com.it.academy.library.service.dto.read.book.BookPublishingHouseReadDto;
import com.it.academy.library.service.entity.book.BookPublishingHouseService;
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
@DisplayName("Book publishing house service test.")
class BookPublishingHouseServiceImplTest extends IntegrationTestBase {
    private static final int BOOK_PUBLISHING_HOUSE_ID_12 = 12;
    private static final String BOOK_PUBLISHING_HOUSE_NAME_POGUE_PRESS = "Pogue Press";
    private static final int BOOK_PUBLISHING_HOUSE_ID_1 = 1;
    private static final String BOOK_PUBLISHING_HOUSE_NAME_APRESS = "Apress";
    private static final int BOOK_PUBLISHING_HOUSE_ID_10 = 10;
    private static final int BOOK_PUBLISHING_HOUSE_ID_99 = 99;
    private static final String BOOK_PUBLISHING_HOUSE_NAME_CROWN = "Crown";

    private final BookPublishingHouseRepository bookPublishingHouseRepository;

    private final BookPublishingHouseService bookPublishingHouseService;

    @Test
    @DisplayName("Save book publishing house.")
    void create() {
        var bookPublishingHouse = new BookPublishingHouseCreateEditDto(ConstantUtil.SAVE + ConstantUtil.NEW);

        var actual = bookPublishingHouseService.create(bookPublishingHouse);

        assertEquals(bookPublishingHouse.getName(), actual.getName());
    }

    @Test
    @DisplayName("Find book publishing house by id.")
    void findById() {
        var expected = new BookPublishingHouseReadDto(
                BOOK_PUBLISHING_HOUSE_ID_12,
                BOOK_PUBLISHING_HOUSE_NAME_POGUE_PRESS
        );

        var actual = bookPublishingHouseService.findById(expected.getId());

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(expected.getId(), entity.getId()),
                        () -> assertEquals(expected.getName(), entity.getName())
                )
        );
    }

    @Test
    @DisplayName("Find all book publishing house.")
    void findAll() {
        var expected = bookPublishingHouseRepository.count();

        var actual = bookPublishingHouseService.findAll();

        assertThat(actual).hasSize(Math.toIntExact(expected));
    }

    @Test
    @DisplayName("Find all book publishing house with filter.")
    void findAllWithFilter() {
        var expected = 1;
        var filter = new BookPublishingHouseFilter();
        filter.setName(BOOK_PUBLISHING_HOUSE_NAME_CROWN);

        var actual = bookPublishingHouseService.findAll(
                filter, Pageable.ofSize(20)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find book publishing house by name.")
    void findByName() {
        var expected = new BookPublishingHouseReadDto(
                BOOK_PUBLISHING_HOUSE_ID_1,
                BOOK_PUBLISHING_HOUSE_NAME_APRESS
        );

        var actual = bookPublishingHouseService.findByName(expected.getName());

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(expected.getName(), entity.getName()),
                        () -> assertEquals(expected.getId(), entity.getId())
                )
        );
    }

    @Test
    @DisplayName("Update book publishing house.")
    void update() {
        var id = 2;
        var bookPublishingHouse = new BookPublishingHouseCreateEditDto(ConstantUtil.UPDATE + ConstantUtil.NEW);

        var actual = bookPublishingHouseService.update(id, bookPublishingHouse);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(entity.getName(), bookPublishingHouse.getName()),
                        () -> assertEquals(entity.getId(), id)
                )
        );
    }

    @Test
    void delete() {
        assertAll(
                () -> assertTrue(bookPublishingHouseService.delete(BOOK_PUBLISHING_HOUSE_ID_10)),
                () -> assertFalse(bookPublishingHouseService.delete(BOOK_PUBLISHING_HOUSE_ID_99))
        );
    }
}