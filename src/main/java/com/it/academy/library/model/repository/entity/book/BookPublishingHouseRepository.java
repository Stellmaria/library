package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.model.repository.filter.book.FilterBookPublishingHouseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookPublishingHouseRepository extends
        JpaRepository<BookPublishingHouse, Integer>,
        FilterBookPublishingHouseRepository,
        QuerydslPredicateExecutor<BookPublishingHouse> {
}
