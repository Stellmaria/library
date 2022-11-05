package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.mapper.filter.book.BookStatusFilterMapper;
import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Book status repository test.")
class BookStatusRepositoryTest extends IntegrationTestBase {
    private static final Integer BOOK_STATUS_ID_3 = 3;
    private static final Integer BOOK_STATUS_ID_4 = 4;

    private final BookStatusRepository bookStatusRepository;
    private final BookStatusFilterMapper bookStatusFilterMapper;

    @Test
    @DisplayName("Save book status.")
    void saveBookStatus() {
        var expectedCount = bookStatusRepository.count() + 1;
        var bookStatus = BookStatus.builder()
                .name(ConstantUtil.NEW + ConstantUtil.SAVE)
                .build();

        var actual = bookStatusRepository.save(bookStatus);
        var actualCount = bookStatusRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getName())
        );
    }

    @Test
    @DisplayName("Delete book status.")
    void deleteBookStatus() {
        var expected = bookStatusRepository.count() - 1;

        bookStatusRepository.deleteById(BOOK_STATUS_ID_4);
        var actual = bookStatusRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update book status.")
    void updateBookStatus() {
        var bookStatus = bookStatusRepository.findById(BOOK_STATUS_ID_3);

        bookStatus.ifPresent(it -> {
            it.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            bookStatusRepository.save(it);
        });
        var actual = bookStatusRepository.findById(BOOK_STATUS_ID_3);

        actual.ifPresent(it -> {
            assertEquals(BOOK_STATUS_ID_3, it.getId());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getName());
        });
    }

    @Test
    @DisplayName("Find book status by book status filter.")
    void findAllBookStatusByBookStatusFilter() {
        var bookStatus = BookStatus.builder()
                .name(ConstantUtil.BOOK_STATUS_NAME_READING_ROOM)
                .build();

        var actual = bookStatusRepository.findAllByBookStatusFilter(bookStatusFilterMapper.map(
                bookStatus));

        assertThat(actual).hasSize(1);
    }
}
