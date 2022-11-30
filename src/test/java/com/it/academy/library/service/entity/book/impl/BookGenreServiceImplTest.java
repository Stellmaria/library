package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.model.repository.entity.book.BookGenreRepository;
import com.it.academy.library.service.dto.create.book.BookGenreCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookGenreFilter;
import com.it.academy.library.service.dto.read.book.BookGenreReadDto;
import com.it.academy.library.service.entity.book.BookGenreService;
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
@DisplayName("Book genre service test.")
class BookGenreServiceImplTest extends IntegrationTestBase {
    private static final String BOOK_GENRE_NAME_CORRUPTION = "Corruption";
    private static final int BOOK_GENRE_ID_11 = 11;
    private static final String BOOK_GENRE_NAME_ROMANCE = "Romance";
    private static final int BOOK_GENRE_ID_8 = 8;
    private static final int BOOK_GENRE_ID_10 = 10;
    private static final int BOOK_GENRE_ID_99 = 99;

    private final BookGenreService bookGenreService;

    private final BookGenreRepository bookGenreRepository;

    @Test
    @DisplayName("Save book genre.")
    void create() {
        var bookGenre = new BookGenreCreateEditDto(ConstantUtil.SAVE + ConstantUtil.NEW);

        var actual = bookGenreService.create(bookGenre);

        assertEquals(bookGenre.getName(), actual.getName());
    }

    @Test
    @DisplayName("Find book genre by id.")
    void findById() {
        var expected = new BookGenreReadDto(
                BOOK_GENRE_ID_11,
                BOOK_GENRE_NAME_CORRUPTION
        );

        var actual = bookGenreService.findById(expected.getId());

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(expected.getId(), entity.getId()),
                        () -> assertEquals(expected.getName(), entity.getName())
                )
        );
    }

    @Test
    @DisplayName("Find all book genre.")
    void findAll() {
        var expected = bookGenreRepository.count();

        var actual = bookGenreService.findAll();

        assertThat(actual).hasSize(Math.toIntExact(expected));
    }

    @Test
    @DisplayName("Find all book genre with filter.")
    void findAllWithFilter() {
        var expected = 1;
        var filter = new BookGenreFilter();
        filter.setName(BOOK_GENRE_NAME_CORRUPTION);

        var actual = bookGenreService.findAll(filter, Pageable.ofSize(20));

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find book genre by name.")
    void findByName() {
        var expected = new BookGenreReadDto(
                BOOK_GENRE_ID_8,
                BOOK_GENRE_NAME_ROMANCE
        );

        var actual = bookGenreService.findByName(expected.getName());

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(expected.getId(), entity.getId()),
                        () -> assertTrue(expected.getName().equalsIgnoreCase(entity.getName()))
                )
        );
    }

    @Test
    @DisplayName("Find book genre by book id.")
    void findAllByBookId() {
        var expected = 1;

        var actual = bookGenreService.findAllByBookId(8L);

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Update book genre.")
    void update() {
        var id = 7;
        var bookGenre = new BookGenreCreateEditDto(ConstantUtil.NEW + ConstantUtil.UPDATE);

        var actual = bookGenreService.update(id, bookGenre);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(bookGenre.getName(), entity.getName()),
                        () -> assertEquals(id, entity.getId())
                )
        );
    }

    @Test
    @DisplayName("Delete book genre.")
    void delete() {
        assertAll(
                () -> assertTrue(bookGenreService.delete(BOOK_GENRE_ID_10)),
                () -> assertFalse(bookGenreService.delete(BOOK_GENRE_ID_99))
        );
    }
}