package com.it.academy.library.mapper.create.book;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.Author;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.model.entity.book.BookLanguage;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.repository.entity.AuthorRepository;
import com.it.academy.library.model.repository.entity.book.BookFormatRepository;
import com.it.academy.library.model.repository.entity.book.BookGenreRepository;
import com.it.academy.library.model.repository.entity.book.BookLanguageRepository;
import com.it.academy.library.model.repository.entity.book.BookPublishingHouseRepository;
import com.it.academy.library.model.repository.entity.book.BookSeriesRepository;
import com.it.academy.library.model.repository.entity.book.BookStatusRepository;
import com.it.academy.library.model.repository.entity.order.OrderRepository;
import com.it.academy.library.service.dto.create.book.BookCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class BookCreateEditMapper implements Mapper<BookCreateEditDto, Book> {
    private final BookStatusRepository bookStatusRepository;
    private final BookLanguageRepository bookLanguageRepository;
    private final BookFormatRepository bookFormatRepository;
    private final BookPublishingHouseRepository bookPublishingHouseRepository;
    private final BookSeriesRepository bookSeriesRepository;
    private final OrderRepository orderRepository;
    private final AuthorRepository authorRepository;
    private final BookGenreRepository bookGenreRepository;

    @Override
    public Book map(BookCreateEditDto fromObject, Book toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    @Override
    public Book map(@NotNull BookCreateEditDto object) {
        var book = new Book();

        copy(object, book);

        return book;
    }

    private void copy(@NotNull BookCreateEditDto object, @NotNull Book book) {
        book.setTitle(object.getTitle());
        book.setSubtitle(object.getSubtitle());
        book.setYear(object.getYear());
        book.setPages(book.getPages());
        book.setIsbn10(object.getIsbn10());
        book.setIsbn13(object.getIsbn13());
        setImage(object, book);
        book.setBookStatus(getBookStatus(object.getBookStatusId()));
        book.setBookLanguage(getBookLanguage(object.getBookLanguageId()));
        book.setBookFormat(getBookFormat(object.getBookFormatId()));
        book.setBookPublishingHouse(getBookPublishingHouseRepository(object.getBookPublishingHouseId()));
        book.setBookSeries(getBookSeries(object.getBookSeriesId()));
        book.setOrder(getOrder(object.getOrderId()));
        book.setAuthors(getAuthors(object));
        book.setGenres(getGenres(object));
    }

    private @NotNull Collection<BookGenre> getGenres(@NotNull BookCreateEditDto object) {
        Collection<BookGenre> bookGenres = new ArrayList<>();

        Objects.requireNonNull(object).getGenresId().stream()
                .map(aLong -> Optional.of(bookGenreRepository.findById(aLong))
                        .orElse(null))
                .forEachOrdered(entity -> entity.ifPresent(bookGenres::add));

        return bookGenres;
    }

    private @NotNull Collection<Author> getAuthors(@NotNull BookCreateEditDto object) {
        Collection<Author> authors = new ArrayList<>();

        Objects.requireNonNull(object).getAuthorsId().stream()
                .map(aLong -> Optional.of(authorRepository.findById(aLong))
                        .orElse(null))
                .forEachOrdered(entity -> entity.ifPresent(authors::add));

        return authors;
    }

    private void setImage(@NotNull BookCreateEditDto object, @NotNull Book book) {
        Optional.ofNullable(object.getImage())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(image -> book.setImage(image.getOriginalFilename()));
    }

    private BookStatus getBookStatus(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(bookStatusRepository::findById)
                .orElse(null);
    }

    private BookLanguage getBookLanguage(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(bookLanguageRepository::findById)
                .orElse(null);
    }

    private BookFormat getBookFormat(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(bookFormatRepository::findById)
                .orElse(null);
    }

    private BookPublishingHouse getBookPublishingHouseRepository(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(bookPublishingHouseRepository::findById)
                .orElse(null);
    }

    private BookSeries getBookSeries(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(bookSeriesRepository::findById)
                .orElse(null);
    }

    private Order getOrder(Long id) {
        return Optional.ofNullable(id)
                .flatMap(orderRepository::findById)
                .orElse(null);
    }
}
