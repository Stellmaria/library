package com.it.academy.library.model.entity.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookLanguage is a Querydsl query type for BookLanguage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookLanguage extends EntityPathBase<BookLanguage> {

    private static final long serialVersionUID = 1778013963L;

    public static final QBookLanguage bookLanguage = new QBookLanguage("bookLanguage");

    public final CollectionPath<Book, QBook> books = this.<Book, QBook>createCollection("books", Book.class, QBook.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QBookLanguage(String variable) {
        super(BookLanguage.class, forVariable(variable));
    }

    public QBookLanguage(Path<? extends BookLanguage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookLanguage(PathMetadata metadata) {
        super(BookLanguage.class, metadata);
    }

}

