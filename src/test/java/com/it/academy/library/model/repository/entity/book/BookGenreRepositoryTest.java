package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.mapper.filter.book.BookFilterMapper;
import com.it.academy.library.mapper.filter.book.BookGenreFilterMapper;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Book genre repository test.")
class BookGenreRepositoryTest extends IntegrationTestBase {
    private final BookGenreRepository bookGenreRepository;

    private final BookGenreFilterMapper bookGenreFilterMapper;
    private final BookFilterMapper bookFilterMapper;

    private final BookGenre bookGenre = new BookGenre();

    @Nested
    @DisplayName("CRUD methods.")
    class CRUD {
        @Test
        @DisplayName("Save book genre.")
        void saveBookGenre() {
            var expectedCount = bookGenreRepository.count() + 1;
            bookGenre.setName(ConstantUtil.NEW + ConstantUtil.SAVE);

            var actual = bookGenreRepository.save(bookGenre);
            var actualCount = bookGenreRepository.count();

            assertAll(
                    () -> assertEquals(expectedCount, actualCount),
                    () -> assertEquals(bookGenre.getName(), actual.getName())
            );
        }

        @Test
        @DisplayName("Delete book genre.")
        void deleteBookGenre() {
            var expected = bookGenreRepository.count() - 1;

            bookGenreRepository.deleteById(ConstantUtil.BOOK_GENRE_ID_4);
            var actual = bookGenreRepository.count();

            assertEquals(expected, actual, "The number of book genres of books must match.");
        }

        @Test
        @DisplayName("Update book genre.")
        void updateBookGenre() {
            var bookGenre = bookGenreRepository.findById(ConstantUtil.BOOK_GENRE_ID_7);

            bookGenre.ifPresent(entity -> {
                entity.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);

                bookGenreRepository.save(entity);
            });
            var actual = bookGenreRepository.findById(ConstantUtil.BOOK_GENRE_ID_7);

            actual.ifPresent(entity ->
                    assertAll(
                            () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getName()),
                            () -> assertEquals(ConstantUtil.BOOK_GENRE_ID_7, entity.getId())
                    )
            );
        }
    }

    @Test
    @DisplayName("Find all book genre by book filter.")
    void findAllBookGenreByBookFilter() {
        var expected = 2;
        var book = new Book();
        book.setTitle(ConstantUtil.BOOK_TITLE_FRAGMENT_PHP);

        var actual = bookGenreRepository.findAllByBookFilter(
                bookFilterMapper.map(book)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book genre by book genre filter.")
    void findAllBookGenreByBookGenreFilter() {
        var expected = 2;
        bookGenre.setName(ConstantUtil.BOOK_GENRE_FRAGMENT_NAME_SS);

        var actual = bookGenreRepository.findAllByBookGenreFilter(
                bookGenreFilterMapper.map(bookGenre)
        );

        assertThat(actual).hasSize(expected);
    }
}
