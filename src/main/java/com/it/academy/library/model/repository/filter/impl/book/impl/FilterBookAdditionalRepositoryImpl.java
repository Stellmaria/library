package com.it.academy.library.model.repository.filter.impl.book.impl;

import com.it.academy.library.model.entity.book.BookAdditional;
import com.it.academy.library.model.repository.filter.impl.book.FilterBookAdditionalRepository;
import com.it.academy.library.service.dto.filter.book.BookAdditionalFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.book.QBookAdditional.bookAdditional;

@RequiredArgsConstructor
public class FilterBookAdditionalRepositoryImpl implements FilterBookAdditionalRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<BookAdditional> findAllByBookAdditionalFilter(
            @NotNull BookAdditionalFilter bookAdditionalFilter) {
        return new JPAQuery<BookAdditional>(entityManager)
                .select(bookAdditional)
                .from(bookAdditional)
                .where(BookAdditionalFilter.queryPredicates(bookAdditionalFilter))
                .fetch();
    }
}