package com.it.academy.library.model.repository.filter.book.impl;

import com.it.academy.library.dto.filter.author.AuthorFilter;
import com.it.academy.library.dto.filter.book.BookAdditionalFilter;
import com.it.academy.library.dto.filter.book.BookFilter;
import com.it.academy.library.dto.filter.book.BookFormatFilter;
import com.it.academy.library.dto.filter.book.BookGenreFilter;
import com.it.academy.library.dto.filter.book.BookLanguageFilter;
import com.it.academy.library.dto.filter.book.BookPublishingHouseFilter;
import com.it.academy.library.dto.filter.book.BookSeriesFilter;
import com.it.academy.library.dto.filter.book.BookStatusFilter;
import com.it.academy.library.dto.filter.order.OrderFilter;
import com.it.academy.library.dto.filter.user.UserFilter;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.repository.filter.book.FilterBookRepository;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Collection;

import static com.it.academy.library.model.entity.QBooksAuthors.booksAuthors;
import static com.it.academy.library.model.entity.QBooksGenres.booksGenres;
import static com.it.academy.library.model.entity.author.QAuthor.author;
import static com.it.academy.library.model.entity.book.QBook.book;
import static com.it.academy.library.model.entity.book.QBookAdditional.bookAdditional;
import static com.it.academy.library.model.entity.book.QBookGenre.bookGenre;
import static com.it.academy.library.model.entity.order.QOrder.order;
import static com.it.academy.library.model.entity.user.QUser.user;

@RequiredArgsConstructor
public class FilterBookRepositoryImpl implements FilterBookRepository {
    private final EntityManager entityManager;

    @Override
    public Collection<Book> findAllByBookFilter(@NotNull BookFilter bookFilter) {
        return new JPAQuery<Book>(entityManager)
                .select(book)
                .from(book)
                .where(BookFilter.queryPredicates(bookFilter))
                .fetch();
    }

    @Override
    public Collection<Book> findAllByBookAdditionalFilter(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return new JPAQuery<Book>(entityManager)
                .select(book)
                .from(bookAdditional)
                .where(BookAdditionalFilter.queryPredicates(bookAdditionalFilter))
                .fetch();
    }

    @Override
    public Collection<Book> findAllByAuthorFilter(@NotNull AuthorFilter authorFilter) {
        return new JPAQuery<Book>(entityManager)
                .select(book)
                .from(book)
                .innerJoin(booksAuthors).on(book.id.eq(booksAuthors.book.id))
                .innerJoin(author).on(author.id.eq(booksAuthors.author.id))
                .where(AuthorFilter.queryPredicates(authorFilter))
                .fetch();
    }

    @Override
    public Collection<Book> findAllByBookGenreFilter(@NotNull BookGenreFilter bookGenreFilter) {
        return new JPAQuery<Book>(entityManager)
                .select(book)
                .from(book)
                .innerJoin(booksGenres).on(book.id.eq(booksGenres.book.id))
                .innerJoin(bookGenre).on(bookGenre.id.eq(booksGenres.bookGenre.id))
                .where(BookGenreFilter.queryPredicates(bookGenreFilter))
                .fetch();
    }

    @Override
    public Collection<Book> findAllByBookLanguageFilter(@NotNull BookLanguageFilter bookLanguageFilter) {
        return new JPAQuery<Book>(entityManager)
                .select(book)
                .from(book)
                .where(BookLanguageFilter.queryPredicates(bookLanguageFilter))
                .fetch();
    }

    @Override
    public Collection<Book> findAllByBookFormatFilter(@NotNull BookFormatFilter bookFormatFilter) {
        return new JPAQuery<Book>(entityManager)
                .select(book)
                .from(book)
                .where(BookFormatFilter.queryPredicates(bookFormatFilter))
                .fetch();
    }

    @Override
    public Collection<Book> findAllByBookPublishingHouseFilter(
            @NotNull BookPublishingHouseFilter bookPublishingHouseFilter) {
        return new JPAQuery<Book>(entityManager)
                .select(book)
                .from(book)
                .where(BookPublishingHouseFilter.queryPredicates(bookPublishingHouseFilter))
                .fetch();
    }

    @Override
    public Collection<Book> findAllByBookStatusFilter(BookStatusFilter bookStatusFilter) {
        return new JPAQuery<Book>(entityManager)
                .select(book)
                .from(book)
                .where(BookStatusFilter.queryPredicates(bookStatusFilter))
                .fetch();
    }

    @Override
    public Collection<Book> findAllByBookSeriesFilter(BookSeriesFilter bookSeriesFilter) {
        return new JPAQuery<Book>(entityManager)
                .select(book)
                .from(book)
                .where(BookSeriesFilter.queryPredicates(bookSeriesFilter))
                .fetch();
    }

    @Override
    public Collection<Book> findAllByOrderFilter(OrderFilter orderFilter) {
        return new JPAQuery<Book>(entityManager)
                .select(book)
                .from(book)
                .innerJoin(order).on(order.id.eq(book.order.id))
                .where(OrderFilter.queryPredicates(orderFilter))
                .fetch();
    }

    @Override
    public Collection<Book> findAllByUserFilter(UserFilter userFilter) {
        return new JPAQuery<Book>(entityManager)
                .select(book)
                .from(user)
                .innerJoin(order).on(user.id.eq(order.user.id))
                .innerJoin(book).on(order.id.eq(book.order.id))
                .where(UserFilter.queryPredicates(userFilter))
                .fetch();
    }
}
