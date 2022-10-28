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

