package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.mapper.filter.book.BookPublishingHouseFilterMapper;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.it.academy.library.util.ConstantUtil.BOOK_PUBLISHING_HOUSE_ID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Book publishing house repository test.")
class BookPublishingHouseFilterRepositoryTest extends IntegrationTestBase {
    private final BookPublishingHouseRepository bookPublishingHouseRepository;

    private final BookPublishingHouseFilterMapper bookPublishingHouseFilterMapper;

    @Test
    @DisplayName("Save book publishing house.")
    void saveBookPublishingHouse() {
        var expectedCount = bookPublishingHouseRepository.count() + 1;
        var bookPublishingHouse = BookPublishingHouse.builder()
                .name(ConstantUtil.NEW + ConstantUtil.SAVE)
                .build();

        var actual = bookPublishingHouseRepository.save(bookPublishingHouse);
        var actualCount = bookPublishingHouseRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getName())
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
        Optional<BookPublishingHouse> bookPublishingHouse;
        bookPublishingHouse = bookPublishingHouseRepository.findById(BOOK_PUBLISHING_HOUSE_ID_1);

        bookPublishingHouse.ifPresent(it -> {
            it.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            bookPublishingHouseRepository.save(it);
        });
        var actual = bookPublishingHouseRepository.findById(BOOK_PUBLISHING_HOUSE_ID_1);

        actual.ifPresent(it -> {
            assertEquals(BOOK_PUBLISHING_HOUSE_ID_1, it.getId());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getName());
        });
    }

    @Test
    @DisplayName("Find all book publishing house by book publishing house.")
    void findAllBookPublishingHouseByBookPublishingHouseFilter() {
        var bookPublishingHouse = BookPublishingHouse.builder()
                .name(ConstantUtil.BOOK_PUBLISHING_HOUSE_FRAGMENT_NAME_BOOKS)
                .build();

        var actual = bookPublishingHouseRepository.findAllByBookPublishingHouseFilter(
                bookPublishingHouseFilterMapper.map(
                        bookPublishingHouse));

        assertThat(actual).hasSize(3);
    }
}
