package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.mapper.filter.book.BookFormatFilterMapper;
import com.it.academy.library.model.entity.book.BookFormat;
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
class BookFormatRepositoryTest extends IntegrationTestBase {
    private final BookFormatRepository bookFormatRepository;

    private final BookFormatFilterMapper bookFormatFilterMapper;

    private final BookFormat bookFormat = new BookFormat();

    @Test
    @DisplayName("Save book format.")
    void saveBookFormat() {
        var expectedCount = bookFormatRepository.count() + 1;
        bookFormat.setName(ConstantUtil.NEW + ConstantUtil.SAVE);

        var actual = bookFormatRepository.save(bookFormat);
        var actualCount = bookFormatRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(bookFormat.getName(), actual.getName())
        );
    }

    @Test
    @DisplayName("Delete book format.")
    void deleteBookFormat() {
        var expected = bookFormatRepository.count() - 1;

        bookFormatRepository.deleteById(ConstantUtil.BOOK_FORMAT_ID_9);
        var actual = bookFormatRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update book format.")
    void updateBookFormat() {
        var bookFormat = bookFormatRepository.findById(ConstantUtil.BOOK_FORMAT_ID_4);

        bookFormat.ifPresent(entity -> {
            entity.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);

            bookFormatRepository.save(entity);
        });
        var actual = bookFormatRepository.findById(ConstantUtil.BOOK_FORMAT_ID_4);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(ConstantUtil.BOOK_FORMAT_ID_4, entity.getId()),
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getName())
                )
        );
    }

    @Test
    @DisplayName("Find all book format by book format filter.")
    void findAllBookFormatByBookFormatFilter() {
        var expected = 2;
        bookFormat.setName(ConstantUtil.BOOK_FORMAT_FRAGMENT_NAME_VER);

        var actual = bookFormatRepository.findAllByBookFormatFilter(
                bookFormatFilterMapper.map(bookFormat)
        );

        assertThat(actual).hasSize(expected);
    }
}
