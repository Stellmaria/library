package com.it.academy.library.model.repository.filter.book.impl;

import com.it.academy.library.dto.filter.book.BookFilter;
import com.it.academy.library.dto.filter.book.BookGenreFilter;
import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.model.repository.filter.book.FilterBookGenreRepository;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.QBooksGenres.booksGenres;
import static com.it.academy.library.model.entity.book.QBook.book;
import static com.it.academy.library.model.entity.book.QBookGenre.bookGenre;

@RequiredArgsConstructor
public class FilterBookGenreRepositoryImpl implements FilterBookGenreRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<BookGenre> findAllByBookGenreFilter(@NotNull BookGenreFilter bookGenreFilter) {
        return new JPAQuery<BookGenre>(entityManager)
                .select(bookGenre)
                .from(bookGenre)
                .where(BookGenreFilter.queryPredicates(bookGenreFilter))
                .fetch();
    }

    @Override
    public Collection<BookGenre> findAllByBookFilter(@NotNull BookFilter bookFilter) {
        return new JPAQuery<BookGenre>(entityManager)
                .select(bookGenre)
                .from(bookGenre)
                .innerJoin(booksGenres).on(bookGenre.id.eq(booksGenres.bookGenre.id))
                .innerJoin(book).on(book.id.eq(booksGenres.book.id))
                .where(BookFilter.queryPredicates(bookFilter))
                .fetch();
    }
}
