package com.it.academy.library.model.repository.filter.impl;

import com.it.academy.library.model.entity.Author;
import com.it.academy.library.model.repository.filter.FilterAuthorRepository;
import com.it.academy.library.service.dto.filter.AuthorFilter;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.QAuthor.author;
import static com.it.academy.library.model.entity.QBooksAuthors.booksAuthors;
import static com.it.academy.library.model.entity.book.QBook.book;

@RequiredArgsConstructor
public class FilterAuthorRepositoryImpl implements FilterAuthorRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<Author> findAllByAuthorFilter(@NotNull AuthorFilter authorFilter) {
        return new JPAQuery<Author>(entityManager)
                .select(author)
                .from(author)
                .where(AuthorFilter.queryPredicates(authorFilter))
                .fetch();
    }

    @Override
    public Collection<Author> findAllByBookFilter(@NotNull BookFilter bookFilter) {
        return new JPAQuery<Author>(entityManager)
                .select(author)
                .from(author)
                .innerJoin(booksAuthors).on(author.id.eq(booksAuthors.author.id))
                .innerJoin(book).on(book.id.eq(booksAuthors.book.id))
                .where(BookFilter.queryPredicates(bookFilter))
                .fetch();
    }
}
