package com.it.academy.library.model.repository.filter.book.impl;

import com.it.academy.library.dto.filter.book.BookFormatFilter;
import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.model.repository.filter.book.FilterBookFormatRepository;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.book.QBookFormat.bookFormat;

@RequiredArgsConstructor
public class FilterBookFormatRepositoryImpl implements FilterBookFormatRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<BookFormat> findAllByBookFormatFilter(@NotNull BookFormatFilter bookFormatFilter) {
        return new JPAQuery<BookFormat>(entityManager)
                .select(bookFormat)
                .from(bookFormat)
                .where(BookFormatFilter.queryPredicates(bookFormatFilter))
                .fetch();
    }
}