package com.it.academy.library.model.entity.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = -943480173L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBook book = new QBook("book");

    public final com.it.academy.library.model.entity.QAbstractAuditingEntity _super = new com.it.academy.library.model.entity.QAbstractAuditingEntity(this);

    public final CollectionPath<BookAdditional, QBookAdditional> bookAdditional = this.<BookAdditional, QBookAdditional>createCollection("bookAdditional", BookAdditional.class, QBookAdditional.class, PathInits.DIRECT2);

    public final QBookFormat bookFormat;

    public final QBookLanguage bookLanguage;

    public final QBookPublishingHouse bookPublishingHouse;

    public final CollectionPath<com.it.academy.library.model.entity.BooksAuthors, com.it.academy.library.model.entity.QBooksAuthors> booksAuthors = this.<com.it.academy.library.model.entity.BooksAuthors, com.it.academy.library.model.entity.QBooksAuthors>createCollection("booksAuthors", com.it.academy.library.model.entity.BooksAuthors.class, com.it.academy.library.model.entity.QBooksAuthors.class, PathInits.DIRECT2);

    public final QBookSeries bookSeries;

    public final CollectionPath<com.it.academy.library.model.entity.BooksGenres, com.it.academy.library.model.entity.QBooksGenres> booksGenres = this.<com.it.academy.library.model.entity.BooksGenres, com.it.academy.library.model.entity.QBooksGenres>createCollection("booksGenres", com.it.academy.library.model.entity.BooksGenres.class, com.it.academy.library.model.entity.QBooksGenres.class, PathInits.DIRECT2);

    public final QBookStatus bookStatus;

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath isbn10 = createString("isbn10");

    public final StringPath isbn13 = createString("isbn13");

    //inherited
    public final DateTimePath<java.time.Instant> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final com.it.academy.library.model.entity.order.QOrder order;

    public final NumberPath<Short> pages = createNumber("pages", Short.class);

    public final StringPath subtitle = createString("subtitle");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QBook(String variable) {
        this(Book.class, forVariable(variable), INITS);
    }

    public QBook(Path<? extends Book> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBook(PathMetadata metadata, PathInits inits) {
        this(Book.class, metadata, inits);
    }

    public QBook(Class<? extends Book> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bookFormat = inits.isInitialized("bookFormat") ? new QBookFormat(forProperty("bookFormat")) : null;
        this.bookLanguage = inits.isInitialized("bookLanguage") ? new QBookLanguage(forProperty("bookLanguage")) : null;
        this.bookPublishingHouse = inits.isInitialized("bookPublishingHouse") ? new QBookPublishingHouse(forProperty("bookPublishingHouse")) : null;
        this.bookSeries = inits.isInitialized("bookSeries") ? new QBookSeries(forProperty("bookSeries")) : null;
        this.bookStatus = inits.isInitialized("bookStatus") ? new QBookStatus(forProperty("bookStatus")) : null;
        this.order = inits.isInitialized("order") ? new com.it.academy.library.model.entity.order.QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

