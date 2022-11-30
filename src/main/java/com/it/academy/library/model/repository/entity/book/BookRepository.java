package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.repository.filter.impl.book.FilterBookRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookRepository extends
        JpaRepository<Book, Long>,
        FilterBookRepository,
        QuerydslPredicateExecutor<Book> {
}
