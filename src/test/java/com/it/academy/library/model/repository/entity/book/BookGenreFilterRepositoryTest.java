package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.mapper.filter.book.BookFilterMapper;
import com.it.academy.library.mapper.filter.book.BookGenreFilterMapper;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Book genre repository test.")
class BookGenreFilterRepositoryTest extends IntegrationTestBase {
    private static final Integer BOOK_GENRE_ID_2 = 2;
    private static final Integer BOOK_GENRE_ID_4 = 4;

    private final BookGenreRepository bookGenreRepository;
    private final BookGenreFilterMapper bookGenreFilterMapper;
    private final BookFilterMapper bookFilterMapper;

    @Test
    @DisplayName("Save book genre.")
    void saveBookGenre() {
        var expectedCount = bookGenreRepository.count() + 1;
        var bookGenre = BookGenre.builder()
                .name(ConstantUtil.NEW + ConstantUtil.SAVE)
                .description(ConstantUtil.NEW + ConstantUtil.SAVE)
                .build();

        var actual = bookGenreRepository.save(bookGenre);
        var actualCount = bookGenreRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getName()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getDescription())
        );
    }

    @Test
    @DisplayName("Delete book genre.")
    void deleteBookGenre() {
        var expected = bookGenreRepository.count() - 1;

        bookGenreRepository.deleteById(BOOK_GENRE_ID_4);
        var actual = bookGenreRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update book genre.")
    void updateBookGenre() {
        var bookGenre = bookGenreRepository.findById(BOOK_GENRE_ID_2);

        bookGenre.ifPresent(it -> {
            it.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setDescription(ConstantUtil.NEW + ConstantUtil.UPDATE);
            bookGenreRepository.save(it);
        });
        var actual = bookGenreRepository.findById(BOOK_GENRE_ID_2);

        actual.ifPresent(it -> {
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getName());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getDescription());
        });
    }

    @Test
    @DisplayName("Find all book genre by book filter.")
    void findAllBookGenreByBookFilter() {
        var book = Book.builder()
                .title(ConstantUtil.BOOK_TITLE_FRAGMENT_PHP)
                .build();

        var actual = bookGenreRepository.findAllByBookFilter(bookFilterMapper.map(book));

        assertThat(actual).hasSize(2);
    }

    @Test
    @DisplayName("Find all book genre by book genre filter.")
    void findAllBookGenreByBookGenreFilter() {
        var bookGenre = BookGenre.builder()
                .name(ConstantUtil.BOOK_GENRE_FRAGMENT_NAME_SS)
                .build();

        var actual = bookGenreRepository.findAllByBookGenreFilter(bookGenreFilterMapper.map(
                bookGenre));

        assertThat(actual).hasSize(2);
    }
}
