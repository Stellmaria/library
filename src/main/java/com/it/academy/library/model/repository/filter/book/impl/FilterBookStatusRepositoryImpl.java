package com.it.academy.library.model.repository.filter.book.impl;

import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.model.repository.filter.book.FilterBookStatusRepository;
import com.it.academy.library.service.dto.filter.book.BookStatusFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.book.QBookStatus.bookStatus;

@RequiredArgsConstructor
public class FilterBookStatusRepositoryImpl implements FilterBookStatusRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<BookStatus> findAllByBookStatusFilter(@NotNull BookStatusFilter bookStatusFilter) {
        return new JPAQuery<BookStatus>(entityManager)
                .select(bookStatus)
                .from(bookStatus)
                .where(BookStatusFilter.queryPredicates(bookStatusFilter))
                .fetch();
    }
}
