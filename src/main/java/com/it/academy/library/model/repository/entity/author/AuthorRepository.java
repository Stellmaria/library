package com.it.academy.library.model.repository.entity.author;

import com.it.academy.library.model.entity.author.Author;
import com.it.academy.library.model.repository.filter.author.FilterAuthorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AuthorRepository extends
        JpaRepository<Author, Long>,
        FilterAuthorRepository,
        QuerydslPredicateExecutor<Author> {
}
