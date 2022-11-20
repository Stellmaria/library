package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.model.repository.filter.impl.book.FilterBookStatusRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookStatusRepository extends
        JpaRepository<BookStatus, Integer>,
        FilterBookStatusRepository,
        QuerydslPredicateExecutor<BookStatus> {
}
