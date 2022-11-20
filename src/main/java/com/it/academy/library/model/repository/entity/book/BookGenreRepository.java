package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.model.repository.filter.impl.book.FilterBookGenreRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookGenreRepository extends
        JpaRepository<BookGenre, Integer>,
        FilterBookGenreRepository,
        QuerydslPredicateExecutor<BookGenre> {
}
