package com.it.academy.library.model.repository.filter.book.impl;

import com.it.academy.library.dto.filter.book.BookPublishingHouseFilter;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.model.repository.filter.book.FilterBookPublishingHouseRepository;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.book.QBookPublishingHouse.bookPublishingHouse;

@RequiredArgsConstructor
public class FilterBookPublishingHouseRepositoryImpl implements FilterBookPublishingHouseRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<BookPublishingHouse> findAllByBookPublishingHouseFilter(
            @NotNull BookPublishingHouseFilter bookPublishingHouseFilter) {
        return new JPAQuery<BookPublishingHouse>(entityManager)
                .select(bookPublishingHouse)
                .from(bookPublishingHouse)
                .where(BookPublishingHouseFilter.queryPredicates(bookPublishingHouseFilter))
                .fetch();
    }
}
