package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.mapper.filter.book.BookPublishingHouseFilterMapper;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.it.academy.library.util.ConstantUtil.BOOK_PUBLISHING_HOUSE_ID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Book publishing house repository test.")
class BookPublishingHouseRepositoryTest extends IntegrationTestBase {
    private final BookPublishingHouseRepository bookPublishingHouseRepository;

    private final BookPublishingHouseFilterMapper bookPublishingHouseFilterMapper;

    private final BookPublishingHouse bookPublishingHouse = new BookPublishingHouse();

    @Test
    @DisplayName("Save book publishing house.")
    void saveBookPublishingHouse() {
        var expectedCount = bookPublishingHouseRepository.count() + 1;
        bookPublishingHouse.setName(ConstantUtil.NEW + ConstantUtil.SAVE);

        var actual = bookPublishingHouseRepository.save(bookPublishingHouse);
        var actualCount = bookPublishingHouseRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(bookPublishingHouse.getName(), actual.getName())
        );
    }

    @Test
    @DisplayName("Delete book publishing house.")
    void deleteBookPublishingHouse() {
        var expected = bookPublishingHouseRepository.count() - 1;

        bookPublishingHouseRepository.deleteById(ConstantUtil.BOOK_PUBLISHING_HOURS_ID_15);
        var actual = bookPublishingHouseRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update book publishing house.")
    void updateBookPublishingHouse() {
        var bookPublishingHouse =
                bookPublishingHouseRepository.findById(BOOK_PUBLISHING_HOUSE_ID_1);

        bookPublishingHouse.ifPresent(entity -> {
            entity.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);

            bookPublishingHouseRepository.save(entity);
        });
        var actual = bookPublishingHouseRepository.findById(BOOK_PUBLISHING_HOUSE_ID_1);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(BOOK_PUBLISHING_HOUSE_ID_1, entity.getId()),
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getName())
                )
        );
    }

    @Test
    @DisplayName("Find all book publishing house by book publishing house.")
    void findAllBookPublishingHouseByBookPublishingHouseFilter() {
        var expected = 3;
        bookPublishingHouse.setName(ConstantUtil.BOOK_PUBLISHING_HOUSE_FRAGMENT_NAME_BOOKS);

        var actual = bookPublishingHouseRepository.findAllByBookPublishingHouseFilter(
                bookPublishingHouseFilterMapper.map(bookPublishingHouse)
        );

        assertThat(actual).hasSize(expected);
    }
}
