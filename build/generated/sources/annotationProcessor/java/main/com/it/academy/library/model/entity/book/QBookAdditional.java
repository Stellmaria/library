package com.it.academy.library.model.entity.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookAdditional is a Querydsl query type for BookAdditional
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookAdditional extends EntityPathBase<BookAdditional> {

    private static final long serialVersionUID = 1814469402L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookAdditional bookAdditional = new QBookAdditional("bookAdditional");

    public final com.it.academy.library.model.entity.QAbstractAuditingEntity _super = new com.it.academy.library.model.entity.QAbstractAuditingEntity(this);

    public final QBook book;

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath link = createString("link");

    //inherited
    public final DateTimePath<java.time.Instant> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<Integer> serialNo = createNumber("serialNo", Integer.class);

    public final NumberPath<Short> volume = createNumber("volume", Short.class);

    public QBookAdditional(String variable) {
        this(BookAdditional.class, forVariable(variable), INITS);
    }

    public QBookAdditional(Path<? extends BookAdditional> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookAdditional(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookAdditional(PathMetadata metadata, PathInits inits) {
        this(BookAdditional.class, metadata, inits);
    }

    public QBookAdditional(Class<? extends BookAdditional> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book"), inits.get("book")) : null;
    }

}

