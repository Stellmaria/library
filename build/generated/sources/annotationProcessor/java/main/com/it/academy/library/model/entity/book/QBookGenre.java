package com.it.academy.library.model.entity.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookGenre is a Querydsl query type for BookGenre
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookGenre extends EntityPathBase<BookGenre> {

    private static final long serialVersionUID = 170007216L;

    public static final QBookGenre bookGenre = new QBookGenre("bookGenre");

    public final com.it.academy.library.model.entity.QAbstractAuditingEntity _super = new com.it.academy.library.model.entity.QAbstractAuditingEntity(this);

    public final CollectionPath<Book, QBook> books = this.<Book, QBook>createCollection("books", Book.class, QBook.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final DateTimePath<java.time.Instant> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath name = createString("name");

    public QBookGenre(String variable) {
        super(BookGenre.class, forVariable(variable));
    }

    public QBookGenre(Path<? extends BookGenre> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookGenre(PathMetadata metadata) {
        super(BookGenre.class, metadata);
    }

}

