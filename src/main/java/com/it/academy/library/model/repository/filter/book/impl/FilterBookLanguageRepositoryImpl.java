package com.it.academy.library.model.repository.filter.book.impl;

import com.it.academy.library.model.entity.book.BookLanguage;
import com.it.academy.library.model.repository.filter.book.FilterBookLanguageRepository;
import com.it.academy.library.service.dto.filter.book.BookLanguageFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.book.QBookLanguage.bookLanguage;

@RequiredArgsConstructor
public class FilterBookLanguageRepositoryImpl implements FilterBookLanguageRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<BookLanguage> findAllByBookLanguageFilter(@NotNull BookLanguageFilter bookLanguageFilter) {
        return new JPAQuery<BookLanguage>(entityManager)
                .select(bookLanguage)
                .from(bookLanguage)
                .where(BookLanguageFilter.queryPredicates(bookLanguageFilter))
                .fetch();
    }
}
