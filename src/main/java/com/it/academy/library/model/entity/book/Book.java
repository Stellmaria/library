package com.it.academy.library.model.entity.book;

import com.it.academy.library.model.entity.AbstractAuditingEntity;
import com.it.academy.library.model.entity.Author;
import com.it.academy.library.model.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@SelectBeforeUpdate
public class Book extends AbstractAuditingEntity<Long> {
    private static final long serialVersionUID = 6120386850837255297L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    private String subtitle;

    @Column(length = 4)
    @Positive
    private Integer year;

    @Positive
    private Long quantity;

    @Positive
    private Short pages;

    @Column(name = "isbn_10",
            length = 10
    )
    @ISBN(type = ISBN.Type.ISBN_10)
    private String isbn10;

    @Column(name = "isbn_13")
    @ISBN(type = ISBN.Type.ISBN_13)
    private String isbn13;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "book_status_id",
            nullable = false
    )
    @ToString.Exclude
    private BookStatus bookStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_language_id")
    @ToString.Exclude
    private BookLanguage bookLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_format_id")
    @ToString.Exclude
    private BookFormat bookFormat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_publishing_house_id")
    @ToString.Exclude
    private BookPublishingHouse bookPublishingHouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_series_id")
    @ToString.Exclude
    private BookSeries bookSeries;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private Order order;

    @ManyToMany(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(
            name = "books_authors",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private Collection<Author> authors;

    @ManyToMany(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(
            name = "books_genres",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")}
    )
    private Collection<BookGenre> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Book book = (Book) o;

        return id != null && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
