package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.mapper.filter.book.BookStatusFilterMapper;
import com.it.academy.library.model.entity.book.BookStatus;
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
    private final BookStatusRepository bookStatusRepository;

    private final BookStatusFilterMapper bookStatusFilterMapper;

    private final BookStatus bookStatus = new BookStatus();

    @Test
    @DisplayName("Save book status.")
    void saveBookStatus() {
        var expectedCount = bookStatusRepository.count() + 1;
        bookStatus.setName(ConstantUtil.NEW + ConstantUtil.SAVE);

        var actual = bookStatusRepository.save(bookStatus);
        var actualCount = bookStatusRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(bookStatus.getName(), actual.getName())
        );
    }

    @Test
    @DisplayName("Delete book status.")
    void deleteBookStatus() {
        var expected = bookStatusRepository.count() - 1;

        bookStatusRepository.deleteById(ConstantUtil.BOOK_STATUS_ID_1);
        var actual = bookStatusRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update book status.")
    void updateBookStatus() {
        var bookStatus = bookStatusRepository.findById(ConstantUtil.BOOK_STATUS_ID_3);
        bookStatus.ifPresent(entity -> {
            entity.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);

            bookStatusRepository.save(entity);
        });

        var actual = bookStatusRepository.findById(ConstantUtil.BOOK_STATUS_ID_3);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(ConstantUtil.BOOK_STATUS_ID_3, entity.getId()),
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getName())
                )
        );
    }

    @Test
    @DisplayName("Find book status by book status filter.")
    void findAllBookStatusByBookStatusFilter() {
        var expected = 1;
        bookStatus.setName(ConstantUtil.BOOK_STATUS_NAME_READING_ROOM);

        var actual = bookStatusRepository.findAllByBookStatusFilter(
                bookStatusFilterMapper.map(bookStatus)
        );

        assertThat(actual).hasSize(expected);
    }
}
