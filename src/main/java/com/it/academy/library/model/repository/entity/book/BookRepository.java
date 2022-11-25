package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.repository.filter.impl.book.FilterBookRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookRepository extends
        JpaRepository<Book, Long>,
        FilterBookRepository,
        QuerydslPredicateExecutor<Book> {

//     TODO: !
//    @Query(value = "SELECT * " +
//            "FROM book " +
//            "WHERE order_id IN (SELECT orders.id " +
//            "                   FROM orders " +
//            "                   WHERE user_id = ?1)",
//            nativeQuery = true
//    )
//    Collection<Book> findAllByOrderForUser(Long id);
}
