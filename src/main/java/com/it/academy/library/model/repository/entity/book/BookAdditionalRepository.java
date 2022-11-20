package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.model.entity.book.BookAdditional;
import com.it.academy.library.model.repository.filter.impl.book.FilterBookAdditionalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookAdditionalRepository extends
        JpaRepository<BookAdditional, Long>,
        FilterBookAdditionalRepository,
        QuerydslPredicateExecutor<BookAdditional> {
}
