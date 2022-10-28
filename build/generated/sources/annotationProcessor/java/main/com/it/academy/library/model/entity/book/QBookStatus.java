package com.it.academy.library.model.entity.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookStatus is a Querydsl query type for BookStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookStatus extends EntityPathBase<BookStatus> {

    private static final long serialVersionUID = 1332274277L;

    public static final QBookStatus bookStatus = new QBookStatus("bookStatus");

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

    public QBookStatus(String variable) {
        super(BookStatus.class, forVariable(variable));
    }

    public QBookStatus(Path<? extends BookStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookStatus(PathMetadata metadata) {
        super(BookStatus.class, metadata);
    }

}

