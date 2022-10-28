package com.it.academy.library.model.entity.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 1827409651L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final com.it.academy.library.model.entity.QAbstractAuditingEntity _super = new com.it.academy.library.model.entity.QAbstractAuditingEntity(this);

    public final CollectionPath<com.it.academy.library.model.entity.book.Book, com.it.academy.library.model.entity.book.QBook> books = this.<com.it.academy.library.model.entity.book.Book, com.it.academy.library.model.entity.book.QBook>createCollection("books", com.it.academy.library.model.entity.book.Book.class, com.it.academy.library.model.entity.book.QBook.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.Instant> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final DateTimePath<java.time.LocalDateTime> orderDate = createDateTime("orderDate", java.time.LocalDateTime.class);

    public final QOrderStatus orderStatus;

    public final QOrderType orderType;

    public final DateTimePath<java.time.LocalDateTime> returnDate = createDateTime("returnDate", java.time.LocalDateTime.class);

    public final com.it.academy.library.model.entity.user.QUser user;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderStatus = inits.isInitialized("orderStatus") ? new QOrderStatus(forProperty("orderStatus")) : null;
        this.orderType = inits.isInitialized("orderType") ? new QOrderType(forProperty("orderType")) : null;
        this.user = inits.isInitialized("user") ? new com.it.academy.library.model.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

