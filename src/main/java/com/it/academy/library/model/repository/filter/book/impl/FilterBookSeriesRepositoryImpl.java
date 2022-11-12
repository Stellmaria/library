package com.it.academy.library.model.repository.filter.book.impl;

import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.model.repository.filter.book.FilterBookSeriesRepository;
import com.it.academy.library.service.dto.filter.book.BookSeriesFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.book.QBookSeries.bookSeries;

@RequiredArgsConstructor
public class FilterBookSeriesRepositoryImpl implements FilterBookSeriesRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<BookSeries> findAllByBookSeriesFilter(@NotNull BookSeriesFilter bookSeriesFilter) {
        return new JPAQuery<BookSeries>(entityManager)
                .select(bookSeries)
                .from(bookSeries)
                .where(BookSeriesFilter.queryPredicates(bookSeriesFilter))
                .fetch();
    }
}
