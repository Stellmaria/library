package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.mapper.filter.book.BookSeriesFilterMapper;
import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Book series repository test.")
class BookSeriesRepositoryTest extends IntegrationTestBase {
    private static final String BOOK_SERIES_DARK_TOWN = "Dark town";
    private static final Integer BOOK_SERIES_ID_1 = 1;
    private static final Integer BOOK_SERIES_ID_2 = 2;

    private final BookSeriesRepository bookSeriesRepository;
    private final BookSeriesFilterMapper bookSeriesFilterMapper;

    @Test
    @DisplayName("Save book series.")
    void saveBookSeries() {
        var expectedCount = bookSeriesRepository.count() + 1;

        var actual = bookSeriesRepository.save(
                BookSeries.builder()
                        .name(ConstantUtil.NEW + ConstantUtil.SAVE)
                        .build()
        );
        var actualCount = bookSeriesRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getName())
        );
    }

    @Test
    @DisplayName("Delete book series")
    void delete() {
        var expected = bookSeriesRepository.count() - 1;

        bookSeriesRepository.deleteById(BOOK_SERIES_ID_1);
        var actual = bookSeriesRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update book series.")
    void updateBookSeries() {
        var bookSeries = bookSeriesRepository.findById(BOOK_SERIES_ID_2);

        bookSeries.ifPresent(it -> {
            it.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);
            bookSeriesRepository.save(it);
        });
        var actual = bookSeriesRepository.findById(BOOK_SERIES_ID_2);

        actual.ifPresent(it -> {
            assertEquals(BOOK_SERIES_ID_2, it.getId());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getName());
        });
    }

    @Test
    @DisplayName("Find all book by book series filter.")
    void findAllBookByBookSeriesFilter() {
        var actual = bookSeriesRepository.findAllByBookSeriesFilter(bookSeriesFilterMapper.map(
                BookSeries.builder()
                        .name(BOOK_SERIES_DARK_TOWN)
                        .build())
        );

        assertThat(actual).hasSize(1);
    }
}
