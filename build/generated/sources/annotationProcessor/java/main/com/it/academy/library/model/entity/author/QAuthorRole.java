package com.it.academy.library.model.entity.author;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuthorRole is a Querydsl query type for AuthorRole
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthorRole extends EntityPathBase<AuthorRole> {

    private static final long serialVersionUID = 1176662953L;

    public static final QAuthorRole authorRole = new QAuthorRole("authorRole");

    public final com.it.academy.library.model.entity.QAbstractAuditingEntity _super = new com.it.academy.library.model.entity.QAbstractAuditingEntity(this);

    public final CollectionPath<Author, QAuthor> authors = this.<Author, QAuthor>createCollection("authors", Author.class, QAuthor.class, PathInits.DIRECT2);

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

    public QAuthorRole(String variable) {
        super(AuthorRole.class, forVariable(variable));
    }

    public QAuthorRole(Path<? extends AuthorRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthorRole(PathMetadata metadata) {
        super(AuthorRole.class, metadata);
    }

}

