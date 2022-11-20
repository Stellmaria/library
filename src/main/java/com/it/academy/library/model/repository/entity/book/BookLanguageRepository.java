package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.model.entity.book.BookLanguage;
import com.it.academy.library.model.repository.filter.impl.book.FilterBookLanguageRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookLanguageRepository extends
        JpaRepository<BookLanguage, Integer>,
        FilterBookLanguageRepository,
        QuerydslPredicateExecutor<BookLanguage> {
}
