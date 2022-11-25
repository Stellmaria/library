package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.mapper.filter.book.BookLanguageFilterMapper;
import com.it.academy.library.model.entity.book.BookLanguage;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Book repository test.")
class BookLanguageFilterRepositoryTest extends IntegrationTestBase {
    private static final String BOOK_LANGUAGE_FRAGMENT_NAME_IAN = "ian";

    private final BookLanguageRepository bookLanguageRepository;

    private final BookLanguageFilterMapper bookLanguageFilterMapper;

    @Test
    @DisplayName("Save book language.")
    void saveBookLanguage() {
        var expectedCount = bookLanguageRepository.count() + 1;
        var bookLanguage = BookLanguage.builder()
                .name(ConstantUtil.NEW + ConstantUtil.SAVE)
                .build();

        var actual = bookLanguageRepository.save(bookLanguage);
        var actualCount = bookLanguageRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getName())
        );
    }

    @Test
    @DisplayName("Delete book language.")
    void deleteBookLanguage() {
        var expected = bookLanguageRepository.count() - 1;

        bookLanguageRepository.deleteById(ConstantUtil.BOOK_LANGUAGE_ID_10);
        var actual = bookLanguageRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update book language.")
    void updateBookLanguage() {
        var bookLanguage = bookLanguageRepository.findById(ConstantUtil.BOOK_LANGUAGE_ID_13);

        bookLanguage.ifPresent(it -> {
            it.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            bookLanguageRepository.save(it);
        });
        var actual = bookLanguageRepository.findById(ConstantUtil.BOOK_LANGUAGE_ID_13);

        actual.ifPresent(it -> {
            assertEquals(ConstantUtil.BOOK_LANGUAGE_ID_13, it.getId());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getName());
        });
    }

    @Test
    @DisplayName("Find all book language by book language filter.")
    void findAllBookLanguageByBookLanguageFilter() {
        var bookLanguage = BookLanguage.builder()
                .name(BOOK_LANGUAGE_FRAGMENT_NAME_IAN)
                .build();

        var actual = bookLanguageRepository.findAllByBookLanguageFilter(
                bookLanguageFilterMapper.map(bookLanguage));

        assertThat(actual).hasSize(21);
    }
}
