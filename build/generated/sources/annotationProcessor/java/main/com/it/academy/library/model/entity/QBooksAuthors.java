package com.it.academy.library.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBooksAuthors is a Querydsl query type for BooksAuthors
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBooksAuthors extends EntityPathBase<BooksAuthors> {

    private static final long serialVersionUID = -1503789927L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBooksAuthors booksAuthors = new QBooksAuthors("booksAuthors");

    public final com.it.academy.library.model.entity.author.QAuthor author;

    public final com.it.academy.library.model.entity.book.QBook book;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QBooksAuthors(String variable) {
        this(BooksAuthors.class, forVariable(variable), INITS);
    }

    public QBooksAuthors(Path<? extends BooksAuthors> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBooksAuthors(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBooksAuthors(PathMetadata metadata, PathInits inits) {
        this(BooksAuthors.class, metadata, inits);
    }

    public QBooksAuthors(Class<? extends BooksAuthors> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.it.academy.library.model.entity.author.QAuthor(forProperty("author"), inits.get("author")) : null;
        this.book = inits.isInitialized("book") ? new com.it.academy.library.model.entity.book.QBook(forProperty("book"), inits.get("book")) : null;
    }

}

