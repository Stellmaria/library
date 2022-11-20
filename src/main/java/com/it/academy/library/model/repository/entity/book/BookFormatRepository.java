package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.model.repository.filter.impl.book.FilterBookFormatRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookFormatRepository extends
        JpaRepository<BookFormat, Integer>,
        FilterBookFormatRepository,
        QuerydslPredicateExecutor<BookFormat> {
}
