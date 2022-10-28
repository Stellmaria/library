package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.model.mapper.filter.book.BookFormatFilterMapper;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Book format repository test.")
class BookFormatFilterRepositoryTest extends IntegrationTestBase {
    private static final Integer BOOK_FORMAT_ID_9 = 9;
    private static final String BOOK_FORMAT_FRAGMENT_NAME_VER = "ver";

    private final BookFormatRepository bookFormatRepository;
    private final BookFormatFilterMapper bookFormatFilterMapper;

    @Test
    @DisplayName("Save book format.")
    void saveBookFormat() {
        var expectedCount = bookFormatRepository.count() + 1;

        var actual = bookFormatRepository.save(
                BookFormat.builder()
                        .name(ConstantUtil.NEW + ConstantUtil.SAVE)
                        .build()
        );
        var actualCount = bookFormatRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getName())
        );
    }

    @Test
    @DisplayName("Delete book format.")
    void deleteBookFormat() {
        var expected = bookFormatRepository.count() - 1;

        bookFormatRepository.deleteById(BOOK_FORMAT_ID_9);
        var actual = bookFormatRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update book format.")
    void updateBookFormat() {
        var bookFormat = bookFormatRepository.findById(ConstantUtil.BOOK_FORMAT_ID_4);

        bookFormat.ifPresent(it -> {
            it.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            bookFormatRepository.save(it);
        });
        var actual = bookFormatRepository.findById(ConstantUtil.BOOK_FORMAT_ID_4);

        actual.ifPresent(it -> {
            assertEquals(ConstantUtil.BOOK_FORMAT_ID_4, it.getId());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getName());
        });
    }

    @Test
    @DisplayName("Find all book format by book format filter.")
    void findAllBookFormatByBookFormatFilter() {
        var actual = bookFormatRepository.findAllByBookFormatFilter(bookFormatFilterMapper.map(
                BookFormat.builder()
                        .name(BOOK_FORMAT_FRAGMENT_NAME_VER)
                        .build())
        );

        assertThat(actual).hasSize(2);
    }
}
