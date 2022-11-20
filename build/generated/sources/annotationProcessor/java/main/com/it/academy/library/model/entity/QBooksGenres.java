package com.it.academy.library.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBooksGenres is a Querydsl query type for BooksGenres
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBooksGenres extends EntityPathBase<BooksGenres> {

    private static final long serialVersionUID = -1831342881L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBooksGenres booksGenres = new QBooksGenres("booksGenres");

    public final com.it.academy.library.model.entity.book.QBook book;

    public final com.it.academy.library.model.entity.book.QBookGenre bookGenre;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QBooksGenres(String variable) {
        this(BooksGenres.class, forVariable(variable), INITS);
    }

    public QBooksGenres(Path<? extends BooksGenres> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBooksGenres(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBooksGenres(PathMetadata metadata, PathInits inits) {
        this(BooksGenres.class, metadata, inits);
    }

    public QBooksGenres(Class<? extends BooksGenres> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new com.it.academy.library.model.entity.book.QBook(forProperty("book"), inits.get("book")) : null;
        this.bookGenre = inits.isInitialized("bookGenre") ? new com.it.academy.library.model.entity.book.QBookGenre(forProperty("bookGenre")) : null;
    }

}

