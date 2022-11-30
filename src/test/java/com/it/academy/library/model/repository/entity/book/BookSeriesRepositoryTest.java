package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.mapper.filter.book.BookSeriesFilterMapper;
import com.it.academy.library.model.entity.book.BookSeries;
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
    private final BookSeriesRepository bookSeriesRepository;

    private final BookSeriesFilterMapper bookSeriesFilterMapper;

    private final BookSeries bookSeries = new BookSeries();

    @Test
    @DisplayName("Save book series.")
    void saveBookSeries() {
        var expectedCount = bookSeriesRepository.count() + 1;
        bookSeries.setName(ConstantUtil.NEW + ConstantUtil.SAVE);

        var actual = bookSeriesRepository.save(bookSeries);
        var actualCount = bookSeriesRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(bookSeries.getName(), actual.getName())
        );
    }

    @Test
    @DisplayName("Delete book series")
    void delete() {
        var expected = bookSeriesRepository.count() - 1;

        bookSeriesRepository.deleteById(ConstantUtil.BOOK_SERIES_ID_1);
        var actual = bookSeriesRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Update book series.")
    void updateBookSeries() {
        var bookSeries = bookSeriesRepository.findById(ConstantUtil.BOOK_SERIES_ID_2);
        bookSeries.ifPresent(entity -> {
            entity.setName(ConstantUtil.NEW + ConstantUtil.UPDATE);

            bookSeriesRepository.save(entity);
        });

        var actual = bookSeriesRepository.findById(ConstantUtil.BOOK_SERIES_ID_2);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(ConstantUtil.BOOK_SERIES_ID_2, entity.getId()),
                        () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getName())
                )
        );
    }

    @Test
    @DisplayName("Find all book by book series filter.")
    void findAllBookByBookSeriesFilter() {
        var expected = 1;
        bookSeries.setName(ConstantUtil.getBookSeriesDarkTown().getName());

        var actual = bookSeriesRepository.findAllByBookSeriesFilter(
                bookSeriesFilterMapper.map(bookSeries)
        );

        assertThat(actual).hasSize(expected);
    }
}
