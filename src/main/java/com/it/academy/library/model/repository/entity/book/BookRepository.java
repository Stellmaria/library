package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.repository.filter.impl.book.FilterBookRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface BookRepository extends
        JpaRepository<Book, Long>,
        FilterBookRepository,
        QuerydslPredicateExecutor<Book> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select book from Book book where book.id = :id")
    Optional<Book> findByIdForUpdate(@Param("id") Long id);
}
