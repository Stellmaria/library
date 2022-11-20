package com.it.academy.library.model.entity.author;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuthor is a Querydsl query type for Author
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthor extends EntityPathBase<Author> {

    private static final long serialVersionUID = -807141229L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuthor author = new QAuthor("author");

    public final com.it.academy.library.model.entity.QAbstractAuditingEntity _super = new com.it.academy.library.model.entity.QAbstractAuditingEntity(this);

    public final QAuthorRole authorRole;

    public final DatePath<java.time.LocalDate> birthday = createDate("birthday", java.time.LocalDate.class);

    public final CollectionPath<com.it.academy.library.model.entity.book.Book, com.it.academy.library.model.entity.book.QBook> books = this.<com.it.academy.library.model.entity.book.Book, com.it.academy.library.model.entity.book.QBook>createCollection("books", com.it.academy.library.model.entity.book.Book.class, com.it.academy.library.model.entity.book.QBook.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final DatePath<java.time.LocalDate> dateDeath = createDate("dateDeath", java.time.LocalDate.class);

    public final StringPath description = createString("description");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath lastName = createString("lastName");

    //inherited
    public final DateTimePath<java.time.Instant> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public QAuthor(String variable) {
        this(Author.class, forVariable(variable), INITS);
    }

    public QAuthor(Path<? extends Author> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuthor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuthor(PathMetadata metadata, PathInits inits) {
        this(Author.class, metadata, inits);
    }

    public QAuthor(Class<? extends Author> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.authorRole = inits.isInitialized("authorRole") ? new QAuthorRole(forProperty("authorRole")) : null;
    }

}

