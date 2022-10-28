package com.it.academy.library.model.entity.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookSeries is a Querydsl query type for BookSeries
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookSeries extends EntityPathBase<BookSeries> {

    private static final long serialVersionUID = 1318916842L;

    public static final QBookSeries bookSeries = new QBookSeries("bookSeries");

    public final com.it.academy.library.model.entity.QAbstractAuditingEntity _super = new com.it.academy.library.model.entity.QAbstractAuditingEntity(this);

    public final CollectionPath<Book, QBook> books = this.<Book, QBook>createCollection("books", Book.class, QBook.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final DateTimePath<java.time.Instant> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath name = createString("name");

    public QBookSeries(String variable) {
        super(BookSeries.class, forVariable(variable));
    }

    public QBookSeries(Path<? extends BookSeries> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookSeries(PathMetadata metadata) {
        super(BookSeries.class, metadata);
    }

}

