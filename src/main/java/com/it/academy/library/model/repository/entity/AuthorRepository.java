package com.it.academy.library.model.repository.entity;

import com.it.academy.library.model.entity.Author;
import com.it.academy.library.model.repository.filter.FilterAuthorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AuthorRepository extends
        JpaRepository<Author, Long>,
        FilterAuthorRepository,
        QuerydslPredicateExecutor<Author> {
}
