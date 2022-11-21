package com.it.academy.library.model.entity.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookFormat is a Querydsl query type for BookFormat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookFormat extends EntityPathBase<BookFormat> {

    private static final long serialVersionUID = 955976810L;

    public static final QBookFormat bookFormat = new QBookFormat("bookFormat");

    public final CollectionPath<Book, QBook> books = this.<Book, QBook>createCollection("books", Book.class, QBook.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QBookFormat(String variable) {
        super(BookFormat.class, forVariable(variable));
    }

    public QBookFormat(Path<? extends BookFormat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookFormat(PathMetadata metadata) {
        super(BookFormat.class, metadata);
    }

}

