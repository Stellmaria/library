package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.model.repository.filter.book.FilterBookSeriesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookSeriesRepository extends
        JpaRepository<BookSeries, Integer>,
        FilterBookSeriesRepository,
        QuerydslPredicateExecutor<BookSeries> {
}
